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

import com.photowey.mongodb.plus.core.util.ObjectUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code Wrapper}
 *
 * @author photowey
 * @date 2023/07/29
 * @since 1.0.0
 */
public abstract class Wrapper<T> implements Serializable {

    protected T document;
    protected Query query;
    protected Criteria criteria;
    protected Class<T> documentClass;

    protected Map<String, Object> pairs;

    public Wrapper() {
        this((T) null);
    }

    public Wrapper(T document) {
        this.document = document;
        this.query = new Query();
        this.criteria = new Criteria();
        this.pairs = new HashMap<>(1 << 4);

        if (ObjectUtils.isNotNull(this.document)) {
            this.documentClass = (Class<T>) this.document.getClass();
        }
    }

    public Wrapper(Class<T> documentClass) {
        this.documentClass = documentClass;
        this.query = new Query();
        this.criteria = new Criteria();
        this.pairs = new HashMap<>(1 << 4);
    }

    public void clear() {
        this.query = new Query();
        this.criteria = new Criteria();
        this.pairs.clear();
    }

    // ----------------------------------------------------------------

    public Class<T> getDocumentClass() {
        return this.documentClass;
    }

    public void setDocumentClass(Class<T> documentClass) {
        this.documentClass = documentClass;
    }

    public T getDocument() {
        return document;
    }

    public void setDocument(T document) {
        this.document = document;
    }

    // ----------------------------------------------------------------

    public T document() {
        return this.getDocument();
    }

    public void document(T document) {
        this.setDocument(document);
    }

    public Class<T> documentClass() {
        return this.getDocumentClass();
    }

    public void documentClass(Class<T> documentClass) {
        this.documentClass = documentClass;
    }

    // ----------------------------------------------------------------

    public Query getQuery() {
        return query;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public Query query() {
        return query;
    }

    public Criteria criteria() {
        return criteria;
    }

    // ----------------------------------------------------------------

    private Type determineGenericType() {
        Type superclass = this.getClass().getGenericSuperclass();
        checkArgument(superclass instanceof ParameterizedType, "%s isn't parameterized", superclass);
        Type runtimeType = ((ParameterizedType) superclass).getActualTypeArguments()[0];

        return runtimeType;
    }

    private static void checkArgument(boolean expression, String message, Object... pn) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, pn));
        }
    }
}
