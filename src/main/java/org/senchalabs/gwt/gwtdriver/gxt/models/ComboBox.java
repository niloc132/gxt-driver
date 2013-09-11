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

import com.google.common.base.Function;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.senchalabs.gwt.gwtdriver.invoke.ClientMethods;
import org.senchalabs.gwt.gwtdriver.invoke.ClientMethodsFactory;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;

import java.util.concurrent.TimeUnit;

@ForWidget(com.sencha.gxt.widget.core.client.form.ComboBox.class)
public class ComboBox extends Field {
	private final ComboBoxMethods methods = ClientMethodsFactory.create(ComboBoxMethods.class, getDriver());

	public ComboBox(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public void clickTrigger() {
		methods.clickTrigger(getElement());
	}
	
	public ListView getDropDown() {
		WebElement listView = methods.getListView(getElement());
		if (listView == null) {
			throw new NoSuchElementException("ComboBox doesn't appear to be expanded");
		}
		//TODO cache this for faster future lookups
		return new ListView(getDriver(), listView);
	}

	public ListView waitForDropDown(long duration, TimeUnit unit) {
		return new FluentWait<WebDriver>(getDriver())
				.withTimeout(duration, unit)
				.ignoring(NotFoundException.class)
				.until(new Function<WebDriver, ListView>() {
					@Override
					public ListView apply(WebDriver webDriver) {
						return getDropDown();
					}
				});
	}
	public ListView waitForDropDown() {
		return waitForDropDown(2, TimeUnit.SECONDS);
	}

	public interface ComboBoxMethods extends ClientMethods {
		/**
		 * Not a real click, but forces the combo box to expand, letting the client implementation
		 * worry about the appearance details.
		 */
		void clickTrigger(WebElement parent);

		/**
		 * Gets the element that represents the listview, provided the combobox is expanded, without
		 * resorting to the expensive search for the popup. Returns null if not expanded.
		 */
		WebElement getListView(WebElement parent);
	}

}
