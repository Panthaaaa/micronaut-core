/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package io.micronaut.inject.qualifiers;

import io.micronaut.context.Qualifier;
import io.micronaut.context.Qualifier;
import io.micronaut.inject.BeanDefinition;
import io.micronaut.inject.BeanType;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A {@link Qualifier} composed of other qualifiers
 *
 * @author Graeme Rocher
 * @since 1.0
 */
class CompositeQualifier<T> implements Qualifier<T> {

    private final Qualifier[] qualifiers;

    CompositeQualifier(Qualifier... qualifiers) {
        this.qualifiers = qualifiers;
    }

    @Override
    public <BT extends BeanType<T>> Stream<BT> reduce(Class<T> beanType, Stream<BT> candidates) {
        Stream<BT> reduced = candidates;
        for (Qualifier qualifier : qualifiers) {
            reduced = qualifier.reduce(beanType,candidates);
        }
        return reduced;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeQualifier<?> that = (CompositeQualifier<?>) o;
        return Arrays.equals(qualifiers, that.qualifiers);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(qualifiers);
    }

    @Override
    public String toString() {
        return Arrays.stream(qualifiers).map(Object::toString).collect(Collectors.joining(" and "));
    }
}