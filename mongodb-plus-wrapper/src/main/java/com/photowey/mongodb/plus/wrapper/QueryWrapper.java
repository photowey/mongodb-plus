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

import java.util.Collection;

/**
 * {@code QueryWrapper}
 *
 * @author photowey
 * @date 2023/08/13
 * @since 1.0.0
 */
public class QueryWrapper<T> extends AbstractWrapper<T, String, QueryWrapper<T>> {

    public QueryWrapper() {
    }

    public QueryWrapper(T document) {
        super(document);
    }

    public QueryWrapper(Class<T> documentClass) {
        super(documentClass);
    }

    @Override
    public QueryWrapper<T> eq(boolean condition, String field, Object value) {
        return this.appendEq(condition, field, value);
    }

    @Override
    public QueryWrapper<T> ne(boolean condition, String field, Object value) {
        return this.appendNe(condition, field, value);
    }

    @Override
    public QueryWrapper<T> gt(boolean condition, String field, Object value) {
        return this.appendGt(condition, field, value);
    }

    @Override
    public QueryWrapper<T> gte(boolean condition, String field, Object value) {
        return this.appendGte(condition, field, value);
    }

    @Override
    public QueryWrapper<T> lt(boolean condition, String field, Object value) {
        return this.appendLt(condition, field, value);
    }

    @Override
    public QueryWrapper<T> lte(boolean condition, String field, Object value) {
        return this.appendLte(condition, field, value);
    }

    @Override
    public QueryWrapper<T> in(boolean condition, String field, Object value) {
        return this.appendIn(condition, field, value);
    }

    @Override
    public QueryWrapper<T> in(boolean condition, String field, Collection<Object> values) {
        return this.appendIn(condition, field, values);
    }

    @Override
    public QueryWrapper<T> notIn(boolean condition, String field, Object value) {
        return this.appendNotIn(condition, field, value);
    }

    @Override
    public QueryWrapper<T> notIn(boolean condition, String field, Collection<Object> values) {
        return this.appendNotIn(condition, field, values);
    }

    @Override
    public void clear() {
        super.clear();
    }
}
