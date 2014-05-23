/*
 * Copyright 2014 Codenvy, S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codenvy.test.framework.concordion;
import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

/**
 * An extension that add to the page JS scripts for converting markdown to html.
 */
public class CodenvyConcordionResourceExtension implements ConcordionExtension {

    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withLinkedJavaScript("/com/codenvy/test/framework/scripts/jquery-1.9.0.min.js",
                                                new Resource("/scripts/jquery-1.9.0.min.js"));
        concordionExtender.withLinkedJavaScript("/com/codenvy/test/framework/scripts/Markdown.ClassConvert.js",
                                                new Resource("/scripts/Markdown.ClassConvert.js"));
        concordionExtender.withLinkedJavaScript("/com/codenvy/test/framework/scripts/Markdown.Converter.js",
                                                new Resource("/scripts/Markdown.Converter.js"));

        // bootstrap
        concordionExtender.withLinkedJavaScript("/com/codenvy/test/framework/bootstrap/js/bootstrap.min.js", new Resource("/bootstrap/js/bootstrap.min.js"));
        concordionExtender.withLinkedCSS("/com/codenvy/test/framework/bootstrap/css/bootstrap.min.css", new Resource("/bootstrap/css/bootstrap.min.css"));
    }
}
