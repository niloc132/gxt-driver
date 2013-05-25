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
import org.senchalabs.gwt.gwtdriver.by.FasterByChained;
import org.senchalabs.gwt.gwtdriver.gxt.models.Button.ButtonFinder;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

import com.sencha.gxt.widget.core.client.button.CellButtonBase;

public class Button extends GwtWidget<ButtonFinder> {

	public Button(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public void click() {
		getElement().findElement(By.xpath("*")).click();
	}

	public String getText() {
		return getElement().getText();
	}

	public static class ButtonFinder extends GwtWidgetFinder<Button> {
		private String text;
		public ButtonFinder withText(String text) {
			this.text = text;
			return this;
		}
		@Override
		public ButtonFinder withDriver(WebDriver driver) {
			return (ButtonFinder) super.withDriver(driver);
		}
		@Override
		public Button done() {
			if (text != null) {
				String escaped = escapeToString(text);
				withElement(driver.findElement(new FasterByChained(By.xpath("//*[contains(text(),"+escaped+")]"), new ByNearestWidget(driver, CellButtonBase.class))));
			}
			return new Button(driver, elt);
		}
	}
}
