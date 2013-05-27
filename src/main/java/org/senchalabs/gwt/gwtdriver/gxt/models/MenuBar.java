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

import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.senchalabs.gwt.gwtdriver.by.ByNearestWidget;
import org.senchalabs.gwt.gwtdriver.by.FasterByChained;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

/**
 *
 */
public class MenuBar extends GwtWidget<MenuBar.MenuBarFinder> {

	public MenuBar(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public static class MenuBarFinder extends GwtWidgetFinder<MenuBar> {
		private String item;

		public MenuBarFinder withItem(String itemText) {
			this.item = itemText;
			return this;
		}

		@Override
		public MenuBar done() {
			WebElement elt = this.elt;
			if (item != null) {

				//find a MenuBarItem with the given text, then find the parent MenuBar
				String escaped = escapeToString(item);

				elt = elt.findElement(new FasterByChained(
						By.xpath(".//*[contains(text(), "+escaped+")]"),
						new ByNearestWidget(driver, MenuBarItem.class),
						new ByNearestWidget(driver, com.sencha.gxt.widget.core.client.menu.MenuBar.class)
				));
			}
			return new MenuBar(driver, elt);
		}
	}

	public Menu click(String text) {
		getElement().findElement(By.xpath(".//*[contains(text(), "+escapeToString(text)+")]")).click();
		return new Menu.MenuFinder().atTop().withDriver(getDriver()).done();
	}
}
