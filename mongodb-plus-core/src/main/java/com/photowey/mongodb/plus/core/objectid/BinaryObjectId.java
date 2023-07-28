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
package com.photowey.mongodb.plus.core.objectid;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@code BinaryObjectId}
 *
 * @author photowey
 * @date 2023/07/27
 * @since 1.0.0
 */
public class BinaryObjectId<T extends Number> {

    public static final Function<String, Integer> CONVERTER_INT = Integer::parseInt;
    public static final Function<String, Long> CONVERTER_LONG = Long::parseLong;
    public static final Function<String, String> CONVERTER_STRING = Function.identity();

    private static final String BINARY_OBJECT_ID_TEMPLATE = "%02d%d%d";
    private static final int SHORTLY_OBJECT_ID_LENGTH = 4;
    private static final int PREFIX_LENGTH = 2;

    /**
     * Complex ObjectId
     * <p>
     * `${objectId}` == `${dragon.length}${dragon}${tiger}`
     *
     * <p>
     * Example:
     * <p>
     * objectId == 1916606142052932321291660542230458118146(19_1660614205293232129_1660542230458118146)
     * <p>
     * dragon == 1660614205293232129
     * <p>
     * tiger == 1660542230458118146
     */
    private final String objectId;
    private final T dragon;
    private final T tiger;
    private final Function<String, T> converter;

    public BinaryObjectId(String objectId, Function<String, T> converter) {
        this.assertNotBlank(objectId, () -> "objectId can't be null");
        this.assertLength(objectId);

        this.objectId = objectId;
        this.converter = converter;

        int dragonLength = Integer.parseInt(this.objectId.substring(0, PREFIX_LENGTH));

        this.dragon = this.converter.apply(this.objectId.substring(PREFIX_LENGTH, PREFIX_LENGTH + dragonLength));
        this.tiger = this.converter.apply(this.objectId.substring(PREFIX_LENGTH + dragonLength));
    }

    public BinaryObjectId(T dragon, T tiger, Function<String, T> converter) {
        this.assertNotNull(dragon, () -> "dragon can't be null");
        this.assertNotNull(tiger, () -> "tiger can't be null");

        this.converter = converter;
        this.dragon = dragon;
        this.tiger = tiger;

        this.objectId = this.determineObjectId(this.dragon, this.tiger);
    }

    // ----------------------------------------------------------------

    private String determineObjectId(T dragon, T tiger) {
        int dragonLength = String.valueOf(dragon).length();
        return String.format(BINARY_OBJECT_ID_TEMPLATE, dragonLength, dragon.longValue(), tiger.longValue());
    }

    // ----------------------------------------------------------------

    private void assertNotBlank(String txt, Supplier<String> getter) {
        if (isBlank(txt)) {
            String message = getter.get();
            throw new IllegalArgumentException(isNotBlank(message) ? message : "Param can't be null");
        }
    }

    private void assertLength(String objectId) {
        this.assertNotBlank(objectId, () -> "ObjectId can't be null");
        int length = objectId.length();
        if (length < SHORTLY_OBJECT_ID_LENGTH) {
            throw new IllegalArgumentException("The length of objectId must be greater than 4");
        }
    }

    private void assertNotNull(T t, Supplier<String> getter) {
        if (null == t) {
            String message = getter.get();
            throw new NullPointerException(isNotBlank(message) ? message : "Param can't be null");
        }
    }

    // ----------------------------------------------------------------

    public static boolean isBlank(String txt) {
        return null == txt || txt.trim().length() == 0;
    }

    public static boolean isNotBlank(String txt) {
        return !isBlank(txt);
    }

    // ----------------------------------------------------------------

    public String getObjectId() {
        return objectId;
    }

    public T getDragon() {
        return dragon;
    }

    public T getTiger() {
        return tiger;
    }

    public String objectId() {
        return objectId;
    }

    public T dragon() {
        return dragon;
    }

    public T tiger() {
        return tiger;
    }
}
