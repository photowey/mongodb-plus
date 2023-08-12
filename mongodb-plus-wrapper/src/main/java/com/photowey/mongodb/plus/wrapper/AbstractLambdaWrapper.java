/*
 * Copyright © 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.photowey.mongodb.plus.wrapper;

import com.photowey.mongodb.plus.core.cache.FieldCache;
import com.photowey.mongodb.plus.core.constant.MongoConstants;
import com.photowey.mongodb.plus.core.fx.SerializeFunction;
import com.photowey.mongodb.plus.core.util.ObjectUtils;
import com.photowey.mongodb.plus.core.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code AbstractLambdaWrapper}
 *
 * @author photowey
 * @date 2023/08/12
 * @since 1.0.0
 */
public abstract class AbstractLambdaWrapper<T, FIELD, CHILDREN extends AbstractLambdaWrapper<T, FIELD, CHILDREN>>
        extends AbstractWrapper<T, SerializeFunction<T, ?>, CHILDREN> {

    public AbstractLambdaWrapper() {
    }

    public AbstractLambdaWrapper(T document) {
        super(document);
    }

    public AbstractLambdaWrapper(Class<T> documentClass) {
        super(documentClass);
    }

    private final Map<String, FieldCache> fieldMap = new HashMap<>(1 << 4);

    @Override
    protected String fieldToString(SerializeFunction<T, ?> field) {
        return this.fieldToString(field, true);
    }

    protected String fieldToString(SerializeFunction<T, ?> field, boolean onlyField) {
        FieldCache cache = this.tryGetFieldCache(field);
        return onlyField ? cache.getField() : cache.getAlias();
    }

    protected FieldCache tryGetFieldCache(SerializeFunction<T, ?> field) {
        String fieldName = SerializeFunction.resolve(field);
        FieldCache cache = this.fieldMap.get(fieldName);
        if (ObjectUtils.isNotNull(cache)) {
            return cache;
        }

        String alias = this.determineFieldAliasIfNecessary(fieldName);
        fieldName = this.wrapFieldIfNecessary(fieldName);
        String defaultAlias = fieldName;
        if (StringUtils.isBlank(alias)) {
            alias = defaultAlias;
        }

        cache = FieldCache.builder()
                .field(fieldName)
                .alias(alias)
                .build();
        this.fieldMap.put(fieldName, cache);

        return cache;
    }

    private String wrapFieldIfNecessary(String fieldName) {
        if (ObjectUtils.isNull(fieldName)) {
            return fieldName;
        }

        if (MongoConstants.DEFAULT_DATABASE_ID.equals(fieldName)) {
            return MongoConstants.DEFAULT_MONGO_ID;
        }


        return fieldName;
    }

    private String determineFieldAliasIfNecessary(String fieldName) {
        if (ObjectUtils.isNotNull(this.documentClass)) {
            for (Field field : this.documentClass.getDeclaredFields()) {
                if (field.getName().equals(fieldName)) {
                    org.springframework.data.mongodb.core.mapping.Field annotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                    if (ObjectUtils.isNotNull(annotation)) {
                        String alias = annotation.value();
                        if (StringUtils.isNotBlank(alias)) {
                            return alias;
                        }
                    }
                }
            }
        }

        return fieldName;
    }
}
