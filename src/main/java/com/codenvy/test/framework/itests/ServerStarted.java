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
package com.codenvy.test.framework.itests;


import com.codenvy.test.framework.AbstractIntegrationTest;
import com.codenvy.test.framework.selenium.pages.IDEMainPage;

import org.openqa.selenium.support.PageFactory;

/**
 * Codenvy SDK basic integration test, see specs reports in src/main/resources/com/codenvy/test/framework/itests/ServerStarted.html
 */
public class ServerStarted extends AbstractIntegrationTest {

    protected IDEMainPage mainPage;

    public String access(String url) {
        driver.get(url);
        mainPage = PageFactory.initElements(driver, IDEMainPage.class);
        return "access";
    }

    public String displayExplorerTab() {
        return mainPage.getTab("Explorer").getText();
    }

    public String displayFileMenu() {
        return mainPage.getMainMenuItem("FileGroup").getText();
    }

}
