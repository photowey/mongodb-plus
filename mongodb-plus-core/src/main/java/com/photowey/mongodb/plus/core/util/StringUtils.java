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

/**
 * {@code StringUtils}
 *
 * @author photowey
 * @date 2023/07/28
 * @since 1.0.0
 */
public final class StringUtils {

    private StringUtils() {
        AssertionErrorThrower.throwz(StringUtils.class);
    }

    public static boolean isBlank(CharSequence txt) {
        if (txt != null) {
            int length = txt.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(txt.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isNotBlank(CharSequence txt) {
        return !isBlank(txt);
    }
}
