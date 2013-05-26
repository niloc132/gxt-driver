package org.senchalabs.gwt.gwtdriver.gxt.models;

/*
 * #%L
 * Sencha GXT classes for gwt-driver
 * %%
 * Copyright (C) 2012 - 2013 Sencha Labs
 * %%
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
 * #L%
 */

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.senchalabs.gwt.gwtdriver.models.GwtLabel;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;

import java.util.concurrent.TimeUnit;

public class WindowTest {
	protected WebDriver driver;
	@Before
	public void startBrowser() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.MILLISECONDS);

		driver.get("http://localhost:9080/app/?"+getScenarioName());
	}

	private String getScenarioName() {
		return "window";
	}

	@After
	public void stopBrowser() {
		driver.close();
	}
	@Test
	public void testFindTopWindow() {
		Window w = GwtWidget.find(Window.class, driver).atTop().done();

		Assert.assertEquals("Test Window A", w.getHeaderElement().getText());
	}

	@Test
	public void testClickNotTopWindow() {
		//test clicking header
		Window w = GwtWidget.find(Window.class, driver).withHeading("Another Test Window").done();
		w.getElement().click();

		w = GwtWidget.find(Window.class, driver).atTop().done();
		Assert.assertEquals("Another Test Window", w.getHeaderElement().getText());

		//test clicking body
		w = GwtWidget.find(Window.class, driver).withHeading("Bottom Window for Testing").done();
		w.find(GwtLabel.class).withText("Contents").done().getElement().click();

		w = GwtWidget.find(Window.class, driver).atTop().done();
		Assert.assertEquals("Bottom Window for Testing", w.getHeaderElement().getText());
	}
	@Test
	public void testCollapseWindows() {

	}

}