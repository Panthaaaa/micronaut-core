/*
 * Copyright 2017-2018 original authors
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

package io.micronaut.inject.visitor;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Stores data about an element that references a method.
 *
 * @author James Kleeh
 * @since 1.0
 */
public interface MethodElement extends VisibileElement {

    /**
     * @return True if the element represents a constructor
     */
    boolean isConstructor();

    /**
     * @return The return type of the method
     */
    @Nullable ClassElement getReturnType();

    /**
     * @return The method parameters, empty if none
     */
    List<ParameterElement> getParameters();

}
