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
package com.codenvy.test.framework;

import com.codenvy.test.framework.concordion.CodenvyConcordionExtension;

import org.concordion.api.extension.Extension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;
import org.grep4j.core.result.GrepResults;
import org.jboss.arquillian.phantom.resolver.ResolvingPhantomJSDriverService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static org.grep4j.core.Grep4j.constantExpression;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.fluent.Dictionary.on;


/**
 * An abstract test that setup concordion and launch phantomjs to be used with webdriver.
 */
@RunWith(ConcordionRunner.class)
public class AbstractIntegrationTest {

    private final static Logger log = Logger.getLogger(AbstractIntegrationTest.class.getName());

    protected WebDriver driver;

    @Extension
    public CodenvyConcordionExtension extension = new CodenvyConcordionExtension();

    @Before
    public void setUp() throws Exception {
        driver = new PhantomJSDriver(
                ResolvingPhantomJSDriverService.createDefaultService(),
                DesiredCapabilities.phantomjs());
        // use firefox for dev testing:
        // driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        extension.setSeleniumDriver(driver);
    }

    @After
    public void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public String serverIsStarted(String logFileLocation) {
        // get server location
        String serverLocation = System.getProperty("codenvy.ide.path");
        final Profile profile = ProfileBuilder.newBuilder()
                                              .name("Local server log")
                                              .filePath(serverLocation + logFileLocation)
                                              .onLocalhost()
                                              .build();
        if (new WebDriverWait(driver, 60).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver arg0) {
                GrepResults results = grep(constantExpression("Server startup in"), on(profile));
                return !results.toString().isEmpty();
            }
        })) {
            return "is started";
        }
        return "is not started";
    }


    public static ExpectedCondition<WebElement> gwtToogleButtonToBeEnable(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public ExpectedCondition<WebElement> visibilityOfElementLocated = ExpectedConditions.visibilityOfElementLocated(locator);

            @Override
            public WebElement apply(WebDriver driver) {
                WebElement element = visibilityOfElementLocated.apply(driver);
                try {
                    if (element != null && !element.getAttribute("class").contains("gwt-ToggleButton-up-disabled")) {
                        return element;
                    } else {
                        return null;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "gwt Toggle Button to be enabled: " + locator;
            }
        };
    }
}
