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
package com.photowey.mongodb.plus.core.annotation.operator;

import java.lang.annotation.*;
import java.util.Objects;

/**
 * {@code FieldStrategy}
 *
 * @author photowey
 * @date 2023/07/28
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldStrategy {

    enum Naming {
        /**
         * 属性源文本
         */
        PLAIN_CASE {
            @Override
            public String convert(String txt) {
                return txt;
            }
        },
        /**
         * 驼峰
         */
        CAMEL_CASE {
            @Override
            public String convert(String txt) {
                if (Objects.isNull(txt) || txt.trim().length() == 0) {
                    return "";
                }
                return txt.substring(0, 1).toLowerCase() + txt.substring(1);
            }
        },
        /**
         * 帕斯卡
         */
        PASCAL_CASE {
            @Override
            public String convert(String txt) {
                if (Objects.isNull(txt) || txt.trim().length() == 0) {
                    return "";
                }
                return txt.substring(0, 1).toUpperCase() + txt.substring(1);
            }
        },
        /**
         * 蛇形
         */
        SNAKE_CASE {
            @Override
            public String convert(String txt) {
                throw new UnsupportedOperationException("UnSupported now.");
            }
        },

        ;

        public abstract String convert(String txt);
    }

}
