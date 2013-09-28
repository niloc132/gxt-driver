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

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.net.NetworkUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
	protected WebDriver driver;

	@Before
	public void startBrowser() {
		String url = System.getProperty("webdriver.remote.server");
		Capabilities c = DesiredCapabilities.internetExplorer();
		driver = new RemoteWebDriver(c);
//		driver = new FirefoxDriver();
		driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.MILLISECONDS);

		driver.get("http://"+new NetworkUtils().getNonLoopbackAddressOfThisMachine()+":9080/app/index.html?"+getScenarioName());
	}

	protected abstract String getScenarioName();

	@After
	public void stopBrowser() {
		driver.quit();
	}
}
