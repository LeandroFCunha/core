/*
 * JBoss, Home of Professional Open Source
 * Copyright 2019, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.environment.se.test.discovery.withAnnotations;

import static org.junit.Assert.assertNull;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Stereotype;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;
import javax.inject.Named;

public class VerifyingExtension implements Extension {

    private AnnotatedType<MyBean> myBeanType;

    private AnnotatedType<MyBeanMeta> myBeanMetaType;

    void processMyBean(@Observes @WithAnnotations(Named.class) ProcessAnnotatedType<MyBean> event) {
        assertNull(myBeanType);
        this.myBeanType = event.getAnnotatedType();
    }

    void processMyBeanMeta(@Observes @WithAnnotations(Stereotype.class) ProcessAnnotatedType<MyBeanMeta> event) {
        assertNull(myBeanMetaType);
        this.myBeanMetaType = event.getAnnotatedType();
    }

    AnnotatedType<MyBean> getMyBeanType() {
        return myBeanType;
    }

    AnnotatedType<MyBeanMeta> getMyBeanMetaType() {
        return myBeanMetaType;
    }

}
