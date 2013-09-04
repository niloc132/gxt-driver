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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.senchalabs.gwt.gwtdriver.by.ByNearestWidget;
import org.senchalabs.gwt.gwtdriver.by.ByWidget;
import org.senchalabs.gwt.gwtdriver.by.FasterByChained;
import org.senchalabs.gwt.gwtdriver.gxt.models.ComboBox.ComboBoxFinder;
import org.senchalabs.gwt.gwtdriver.invoke.ClientMethods;
import org.senchalabs.gwt.gwtdriver.invoke.ClientMethodsFactory;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

import com.sencha.gxt.widget.core.client.form.FieldLabel;

@ForWidget(com.sencha.gxt.widget.core.client.form.ComboBox.class)
public class ComboBox extends GwtWidget<ComboBoxFinder> {
	
	private final ComboBoxMethods methods = ClientMethodsFactory.create(ComboBoxMethods.class, getDriver());

	public ComboBox(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public void clickTrigger() {
		methods.clickTrigger(getElement());
	}
	
	public void sendKeys(CharSequence... keys) {
		getElement().findElement(By.tagName("input")).sendKeys(keys);
	}

	public interface ComboBoxMethods extends ClientMethods {
		/**
		 * Not a real click, but forces the combo box to expand.
		 * @param parent
		 */
		void clickTrigger(WebElement parent);
	}

	public static class ComboBoxFinder extends GwtWidgetFinder<ComboBox> {
		private String text;
		public ComboBoxFinder withText(String text) {
			this.text = text;
			return this;
		}
		
		private String fieldLabel;
		public ComboBoxFinder withLabel(String label) {
			this.fieldLabel = label;
			return this;
		}
		@Override
		public ComboBoxFinder withDriver(WebDriver driver) {
			return (ComboBoxFinder) super.withDriver(driver);
		}
		@Override
		public ComboBox done() {
			WebElement elt = this.elt;
			if (fieldLabel != null) {
				String escaped = escapeToString(fieldLabel);
				elt = elt.findElement(new FasterByChained(By.xpath(".|.//*[contains(text(), "+escaped+")]"),
						new ByNearestWidget(driver, FieldLabel.class),
						new FasterByChained(
								By.xpath("*|*/*"),
								new ByWidget(driver, com.sencha.gxt.widget.core.client.form.ComboBox.class)
						)));
				return new ComboBox(driver, elt);
			} else if (text != null) {
				String escaped = escapeToString(text);
				return new ComboBox(driver, elt.findElement(new FasterByChained(
						By.xpath(".|.//*[contains(text(), "+escaped+")]"),
						new ByNearestWidget(driver, com.sencha.gxt.widget.core.client.form.ComboBox.class)
						)));
			} else {
				WebElement foundElement = elt.findElement(new FasterByChained(By.xpath(".//*"), 
						new ByWidget(driver, com.sencha.gxt.widget.core.client.form.ComboBox.class)));
				return new ComboBox(driver, foundElement);
			}
		}
	}
	
}
