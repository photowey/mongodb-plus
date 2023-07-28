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
package com.photowey.mongodb.plus.core.fx;

import com.photowey.mongodb.plus.core.shared.org.apache.ibatis.reflection.property.PropertyNamer;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * {@code SerializeFunction}
 *
 * @author photowey
 * @date 2023/07/28
 * @since 1.0.0
 */
@FunctionalInterface
public interface SerializeFunction<T, R> extends Function<T, R>, Serializable {

    static <T, R> String resolve(SerializeFunction<T, R> fx) {
        try {
            Method method = fx.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            java.lang.invoke.SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fx);
            String getter = serializedLambda.getImplMethodName();
            return PropertyNamer.methodToProperty(getter);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
