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
package com.photowey.mongodb.plus.core.util;

import com.photowey.mongodb.plus.core.thrower.AssertionErrorThrower;

import java.util.Collection;
import java.util.Map;

/**
 * {@code ObjectUtils}
 *
 * @author photowey
 * @date 2023/07/28
 * @since 1.0.0
 */
public final class ObjectUtils {

    private ObjectUtils() {
        AssertionErrorThrower.throwz(ObjectUtils.class);
    }

    public static <T> boolean isNotNull(T v) {
        if (v instanceof String) {
            return StringUtils.isNotBlank((CharSequence) v);
        } else if (v instanceof Collection) {
            return CollectionUtils.isNotEmpty((Collection<?>) v);
        } else if (v instanceof Map) {
            return CollectionUtils.isNotEmpty((Map<?, ?>) v);
        }

        return null != v;
    }

    public static <T> boolean isNull(T v) {
        return !isNotNull(v);
    }
}
