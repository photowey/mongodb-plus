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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@code StringUtilsTest}
 *
 * @author photowey
 * @date 2023/07/28
 * @since 1.0.0
 */
class StringUtilsTest {

    @Test
    void testIsBlank() {
        Assertions.assertTrue(StringUtils.isBlank(null));
        Assertions.assertTrue(StringUtils.isBlank(" "));
        Assertions.assertTrue(StringUtils.isBlank(" "));

        Assertions.assertFalse(StringUtils.isBlank("A"));
        Assertions.assertFalse(StringUtils.isBlank(" A"));
        Assertions.assertFalse(StringUtils.isBlank("A "));
        Assertions.assertFalse(StringUtils.isBlank(" A "));

        Assertions.assertFalse(StringUtils.isNotBlank(null));
        Assertions.assertFalse(StringUtils.isNotBlank(""));
        Assertions.assertFalse(StringUtils.isNotBlank(" "));

        Assertions.assertTrue(StringUtils.isNotBlank("A"));
        Assertions.assertTrue(StringUtils.isNotBlank(" A "));
        Assertions.assertTrue(StringUtils.isNotBlank(" AB "));
        Assertions.assertTrue(StringUtils.isNotBlank(" AB"));
        Assertions.assertTrue(StringUtils.isNotBlank("AB "));
    }

}