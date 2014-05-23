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
package com.codenvy.test.framework.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The main webdrive page object for driving Codenvy IDE
 */
public class IDEMainPage {

    protected WebDriver driver;

    public IDEMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTab(String tabName) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("gwt-debug-tabButton-" + tabName)));
    }

    public WebElement getMainMenuItem(String mainMenuItemName) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("gwt-debug-MainMenu/"
                                                                                                     + mainMenuItemName)));
    }

    public WebElement getMainMenuAction(String menuPath) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id("topmenu/" + menuPath)));
    }
}
