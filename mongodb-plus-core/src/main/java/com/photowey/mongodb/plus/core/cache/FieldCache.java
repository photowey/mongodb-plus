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
package com.photowey.mongodb.plus.core.cache;

import java.io.Serializable;
import java.util.Objects;

/**
 * {@code FieldCache}
 *
 * @author photowey
 * @date 2023/07/29
 * @since 1.0.0
 */
public class FieldCache implements Serializable {

    private String field;
    private String alias;
    private String mapping;

    // ----------------------------------------------------------------

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMapping() {
        return this.mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldCache that = (FieldCache) o;
        return Objects.equals(field, that.field) && Objects.equals(alias, that.alias) && Objects.equals(mapping, that.mapping);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, alias, mapping);
    }

    @Override
    public String toString() {
        return "FieldCache{" +
                "field='" + field + '\'' +
                ", alias='" + alias + '\'' +
                ", mapping='" + mapping + '\'' +
                '}';
    }

    public static FieldCacheBuilder builder() {
        return new FieldCacheBuilder();
    }

    public FieldCache() {
    }

    public FieldCache(String field, String alias) {
        this.field = field;
        this.alias = alias;
    }

    public FieldCache(final String field, final String alias, final String mapping) {
        this.field = field;
        this.alias = alias;
        this.mapping = mapping;
    }

    public static class FieldCacheBuilder {
        private String field;
        private String alias;
        private String mapping;

        FieldCacheBuilder() {
        }

        public FieldCacheBuilder field(final String field) {
            this.field = field;
            return this;
        }

        public FieldCacheBuilder alias(final String alias) {
            this.alias = alias;
            return this;
        }

        public FieldCacheBuilder mapping(final String mapping) {
            this.mapping = mapping;
            return this;
        }

        public FieldCache build() {
            return new FieldCache(this.field, this.alias, this.mapping);
        }

        public String toString() {
            return "FieldCache.FieldCacheBuilder(field=" + this.field + ", alias=" + this.alias + ", mapping=" + this.mapping + ")";
        }
    }
}
