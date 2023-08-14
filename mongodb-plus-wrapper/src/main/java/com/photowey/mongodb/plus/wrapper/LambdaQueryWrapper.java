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

import com.photowey.mongodb.plus.core.fx.SerializeFunction;

import java.util.Collection;

/**
 * {@code LambdaQueryWrapper}
 *
 * @author photowey
 * @date 2023/08/14
 * @since 1.0.0
 */
public class LambdaQueryWrapper<T> extends AbstractLambdaWrapper<T, SerializeFunction<T, ?>, LambdaQueryWrapper<T>> {

    public LambdaQueryWrapper() {
        super();
    }

    public LambdaQueryWrapper(T document) {
        super(document);
    }

    public LambdaQueryWrapper(Class<T> documentClass) {
        super(documentClass);
    }

    @Override
    public LambdaQueryWrapper<T> eq(boolean condition, SerializeFunction<T, ?> field, Object value) {
        return this.appendEq(condition, field, value);
    }

    @Override
    public LambdaQueryWrapper<T> ne(boolean condition, SerializeFunction<T, ?> field, Object value) {
        return this.appendNe(condition, field, value);
    }

    @Override
    public LambdaQueryWrapper<T> gt(boolean condition, SerializeFunction<T, ?> field, Object value) {
        return this.appendGt(condition, field, value);
    }

    @Override
    public LambdaQueryWrapper<T> gte(boolean condition, SerializeFunction<T, ?> field, Object value) {
        return this.appendGte(condition, field, value);
    }

    @Override
    public LambdaQueryWrapper<T> lt(boolean condition, SerializeFunction<T, ?> field, Object value) {
        return this.appendLt(condition, field, value);
    }

    @Override
    public LambdaQueryWrapper<T> lte(boolean condition, SerializeFunction<T, ?> field, Object value) {
        return this.appendLte(condition, field, value);
    }

    @Override
    public LambdaQueryWrapper<T> in(boolean condition, SerializeFunction<T, ?> field, Object value) {
        return this.appendIn(condition, field, value);
    }

    @Override
    public LambdaQueryWrapper<T> in(boolean condition, SerializeFunction<T, ?> field, Collection<Object> values) {
        return this.appendIn(condition, field, values);
    }

    @Override
    public LambdaQueryWrapper<T> notIn(boolean condition, SerializeFunction<T, ?> field, Object value) {
        return this.appendNotIn(condition, field, value);
    }

    @Override
    public LambdaQueryWrapper<T> notIn(boolean condition, SerializeFunction<T, ?> field, Collection<Object> values) {
        return this.appendNotIn(condition, field, values);
    }

    @Override
    public void clear() {
        super.clear();
    }
}
