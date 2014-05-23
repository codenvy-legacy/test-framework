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

import org.concordion.ext.ScreenshotTaker;
import org.concordion.ext.ScreenshotUnavailableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.io.OutputStream;

public class SeleniumScreenshotTaker implements ScreenshotTaker {

    private final WebDriver driver;

    public SeleniumScreenshotTaker(WebDriver driver) {
        WebDriver baseDriver = driver;
        while (baseDriver instanceof EventFiringWebDriver) {
            baseDriver = ((EventFiringWebDriver)baseDriver).getWrappedDriver();
        }
        this.driver = baseDriver;
    }

    public int writeScreenshotTo(OutputStream outputStream) throws IOException {
        byte[] screenshot;
        try {
            screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        } catch (ClassCastException e) {
            throw new ScreenshotUnavailableException("driver does not implement TakesScreenshot");
        }
        outputStream.write(screenshot);
        return ((Long)((JavascriptExecutor)driver).executeScript("return document.body.clientWidth")).intValue() + 2; // window.outerWidth"));
    }

    public String getFileExtension() {
        return "png";
    }
}
