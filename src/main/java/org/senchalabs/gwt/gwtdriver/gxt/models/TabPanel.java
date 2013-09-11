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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.senchalabs.gwt.gwtdriver.by.ByNearestWidget;
import org.senchalabs.gwt.gwtdriver.by.ByWidget;
import org.senchalabs.gwt.gwtdriver.by.FasterByChained;
import org.senchalabs.gwt.gwtdriver.gxt.models.TabPanel.TabPanelFinder;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

import java.util.List;

@ForWidget(com.sencha.gxt.widget.core.client.TabPanel.class)
public class TabPanel extends GwtWidget<TabPanelFinder> {

	public TabPanel(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public static class TabPanelFinder extends GwtWidgetFinder<TabPanel> {
		private String itemText;
		public TabPanelFinder withItem(String item) {
			this.itemText = item;
			return this;
		}
		@Override
		public TabPanel done() {
			WebElement elt;
			if (this.itemText != null) {
				//find the text in the tabpanel, but not in a widget that isn't a tabpanel
				//this isn't terribly cheap...
				elt = this.elt.findElement(new FasterByChained(
						By.xpath(".//*[contains(text(),"+escapeToString(itemText)+")]"),
						new ByNearestWidget(driver),
						new ByWidget(driver, com.sencha.gxt.widget.core.client.TabPanel.class)
				));

			} else {
				elt = this.elt;
			}

			return new TabPanel(driver, elt);
		}
	}

	/**
	 * Attempts a right click in the browser to show the context menu. Must be enabled in the client code, or no menu will be found
	 * @see com.sencha.gxt.widget.core.client.TabPanel#setCloseContextMenu(boolean)
	 */
	public Menu rightClickTabWithText(String text) {
		List<WebElement> elements = getElement().findElements(By.xpath(".//*[contains(text()," + escapeToString(text) + ")]"));
		for (WebElement elt : elements) {
			if (!elt.findElements(new FasterByChained(
					new ByNearestWidget(getDriver()),
					new ByWidget(getDriver(), com.sencha.gxt.widget.core.client.TabPanel.class)
			)).isEmpty()) {
				Actions a = new Actions(getDriver());
				a.contextClick(elt);
				a.perform();
				return GwtWidget.find(Menu.class, getDriver()).atTop().done();
			}
		}
		throw new NoSuchElementException("No tab found with text '" + text + "'");
	}
	public void clickTabWithText(String text) {
		List<WebElement> elements = getElement().findElements(By.xpath(".//*[contains(text()," + escapeToString(text) + ")]"));
		for (WebElement elt : elements) {
			if (!elt.findElements(new FasterByChained(
					new ByNearestWidget(getDriver()),
					new ByWidget(getDriver(), com.sencha.gxt.widget.core.client.TabPanel.class)
			)).isEmpty()) {
				elt.click();
				return;
			}
		}
		throw new NoSuchElementException("No tab found with text '" + text + "'");
	}
}
