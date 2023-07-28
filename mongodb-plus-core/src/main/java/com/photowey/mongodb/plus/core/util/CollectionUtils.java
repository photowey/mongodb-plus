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

import java.util.*;

/**
 * {@code CollectionUtils}
 *
 * @author photowey
 * @date 2023/07/28
 * @since 1.0.0
 */
public final class CollectionUtils {

    private CollectionUtils() {
        AssertionErrorThrower.throwz(CollectionUtils.class);
    }

    public static <T> Set<T> newHashSet() {
        return new HashSet<>();
    }

    @SafeVarargs
    public static <T> Set<T> newHashSet(T... ts) {
        return newHashSet(Arrays.asList(ts));
    }

    public static <T> Set<T> newHashSet(int size) {
        return new HashSet<>(calculateInitialCapacityFromExpectedSize(size));
    }

    public static <T> Set<T> newHashSet(Collection<? extends T> collection) {
        return new HashSet<>(collection);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    @SafeVarargs
    public static <T> List<T> newArrayList(T... ts) {
        return newArrayList(Arrays.asList(ts));
    }

    public static <T> List<T> newArrayList(Collection<? extends T> collection) {
        return new ArrayList<>(collection);
    }

    // ----------------------------------------------------------------

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    // ----------------------------------------------------------------

    private static int calculateInitialCapacityFromExpectedSize(int expectedSize) {
        return expectedSize < 3 ? expectedSize + 1 : (int) ((float) expectedSize / 0.75F + 1.0F);
    }
}
