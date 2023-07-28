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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

/**
 * {@code SerializeFunctionTest}
 *
 * @author photowey
 * @date 2023/07/28
 * @since 1.0.0
 */
class SerializeFunctionTest {

    @Test
    void testResolve() {
        String id = SerializeFunction.resolve(Person::getId);
        String name = SerializeFunction.resolve(Person::getName);
        String age = SerializeFunction.resolve(Person::getAge);
        String helloWorld = SerializeFunction.resolve(Person::getHelloWorld);
        String successful = SerializeFunction.resolve(Person::isSuccessful);

        Assertions.assertEquals("id", id);
        Assertions.assertEquals("name", name);
        Assertions.assertEquals("age", age);
        Assertions.assertEquals("helloWorld", helloWorld);
        Assertions.assertEquals("successful", successful);
    }

    public static class Person implements Serializable {

        private static final long serialVersionUID = -1316830162729572943L;

        private Long id;
        private String name;
        private Integer age;
        private String helloWorld;
        private boolean successful;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getHelloWorld() {
            return helloWorld;
        }

        public void setHelloWorld(String helloWorld) {
            this.helloWorld = helloWorld;
        }

        public boolean isSuccessful() {
            return successful;
        }

        public void setSuccessful(boolean successful) {
            this.successful = successful;
        }
    }
}