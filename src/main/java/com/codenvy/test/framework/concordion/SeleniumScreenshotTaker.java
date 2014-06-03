/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
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
