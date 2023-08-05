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

import com.photowey.mongodb.plus.core.fx.FieldVisitor;
import com.photowey.mongodb.plus.core.util.CollectionUtils;
import com.photowey.mongodb.plus.core.util.ObjectUtils;
import com.photowey.mongodb.plus.wrapper.condition.Comparable;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@code AbstractWrapper}
 *
 * @author photowey
 * @date 2023/08/05
 * @since 1.0.0
 */
public abstract class AbstractWrapper<T, FIELD, CHILDREN extends AbstractWrapper<T, FIELD, CHILDREN>>
        extends Wrapper<T> implements Comparable<CHILDREN, FIELD> {

    protected final CHILDREN wrappedThis = (CHILDREN) this;

    @Override
    public <V> CHILDREN eq(Map<FIELD, V> pairs) {
        if (CollectionUtils.isNotEmpty(pairs)) {
            pairs.forEach(this::eq);
        }

        return wrappedThis;
    }

    // ----------------------------------------------------------------

    protected CHILDREN appendEq(boolean condition, FIELD field, Object value) {
        return this.eq(condition, field, value, this::fieldToString);
    }

    protected CHILDREN appendNe(boolean condition, FIELD field, Object value) {
        return this.eq(condition, field, value, this::fieldToString);
    }

    protected CHILDREN appendIn(boolean condition, FIELD field, Object value) {
        return this.in(condition, field, value, this::fieldToString);
    }

    protected CHILDREN appendIn(boolean condition, FIELD field, Collection<Object> values) {
        return this.in(condition, field, values, this::fieldToString);
    }

    protected CHILDREN appendNotIn(boolean condition, FIELD field, Object value) {
        return this.notIn(condition, field, value, this::fieldToString);
    }

    protected CHILDREN appendNotIn(boolean condition, FIELD field, Collection<Object> values) {
        return this.notIn(condition, field, values, this::fieldToString);
    }

    // ----------------------------------------------------------------

    protected CHILDREN eq(boolean condition, FIELD field, Object value, Stringer<FIELD> fx) {
        return this.eq(condition, value, () -> Criteria.where(fx.apply(field)).is(value));
    }

    protected CHILDREN eq(boolean condition, Object value, Supplier<Criteria> fx) {
        return this.deduce(condition, value, () -> this.criteria.andOperator(fx.get()));
    }

    // ----------------------------------------------------------------

    protected CHILDREN ne(boolean condition, FIELD field, Object value, Stringer<FIELD> fx) {
        return this.ne(condition, value, () -> Criteria.where(fx.apply(field)).ne(value));
    }

    protected CHILDREN ne(boolean condition, Object value, Supplier<Criteria> fx) {
        return this.deduce(condition, value, () -> this.criteria.andOperator(fx.get()));
    }

    // ----------------------------------------------------------------

    protected CHILDREN in(boolean condition, FIELD field, Object value, Stringer<FIELD> fx) {
        return this.in(condition, value, () -> Criteria.where(fx.apply(field)).in(value));
    }

    protected CHILDREN in(boolean condition, Object value, Supplier<Criteria> fx) {
        return this.deduce(condition, value, () -> this.criteria.andOperator(fx.get()));
    }

    protected CHILDREN in(boolean condition, FIELD field, Collection<Object> values, Stringer<FIELD> fx) {
        return this.in(condition, values, () -> Criteria.where(fx.apply(field)).in(values));
    }

    protected CHILDREN in(boolean condition, Collection<Object> values, Supplier<Criteria> fx) {
        return this.deduce(condition, values, () -> this.criteria.andOperator(fx.get()));
    }

    // ----------------------------------------------------------------

    protected CHILDREN notIn(boolean condition, FIELD field, Object value, Stringer<FIELD> fx) {
        return this.notIn(condition, value, () -> Criteria.where(fx.apply(field)).nin(value));
    }

    protected CHILDREN notIn(boolean condition, Object value, Supplier<Criteria> fx) {
        return this.deduce(condition, value, () -> this.criteria.andOperator(fx.get()));
    }

    protected CHILDREN notIn(boolean condition, FIELD field, Collection<Object> values, Stringer<FIELD> fx) {
        return this.notIn(condition, values, () -> Criteria.where(fx.apply(field)).nin(values));
    }

    protected CHILDREN notIn(boolean condition, Collection<Object> values, Supplier<Criteria> fx) {
        return this.deduce(condition, values, () -> this.criteria.andOperator(fx.get()));
    }

    // ----------------------------------------------------------------

    protected String fieldToString(FIELD field) {
        return String.valueOf(field);
    }

    // ----------------------------------------------------------------

    protected final CHILDREN deduce(boolean condition, FieldVisitor visitor) {
        return this.deduce(condition, 1, visitor);
    }

    protected final CHILDREN deduce(boolean condition, Object value, FieldVisitor visitor) {
        if (condition && ObjectUtils.isNotNull(value)) {
            visitor.visit();
        }

        return wrappedThis;
    }

    // ----------------------------------------------------------------

    interface Stringer<FIELD> extends Function<FIELD, String> {
    }
}
