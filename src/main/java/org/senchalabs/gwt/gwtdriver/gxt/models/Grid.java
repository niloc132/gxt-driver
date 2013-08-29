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
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.senchalabs.gwt.gwtdriver.by.ByNearestWidget;
import org.senchalabs.gwt.gwtdriver.by.ByWidget;
import org.senchalabs.gwt.gwtdriver.by.FasterByChained;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

@ForWidget(com.sencha.gxt.widget.core.client.grid.Grid.class)
public class Grid extends GwtWidget<GwtWidgetFinder<Grid>> {

	public Grid(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public static class ColumnHeader extends GwtWidget<GwtWidgetFinder<ColumnHeader>> {
		public ColumnHeader(WebDriver driver, WebElement element) {
			super(driver, element);
		}
		public void click() {
			getElement().click();
		}
		public Menu clickContextMenu() {
			Coordinates loc = ((Locatable)getElement()).getCoordinates();
			((HasInputDevices)getDriver()).getMouse().mouseMove(loc);
			getElement().findElement(By.tagName("a")).click();
			return new Menu.MenuFinder().atTop().withDriver(getDriver()).done();
		}
	}

	public ColumnHeader getColumnHeaderWithHeading(String headingText) {
		WebElement elt = getElement().findElement(new FasterByChained(
				By.xpath(".//div[a][img]//*[contains(text(), " + escapeToString(headingText) + ")]"),
				new ByNearestWidget(getDriver(), com.sencha.gxt.widget.core.client.grid.ColumnHeader.Head.class)));
		return new ColumnHeader(getDriver(), elt);
	}

	public ColumnHeader getColumnHeaderWithIndex(int index) {
		WebElement elt = getElement().findElements(new ByChained(By.xpath(".//div[a][img]"), new ByWidget(getDriver(), com.sencha.gxt.widget.core.client.grid.ColumnHeader.Head.class))).get(index);
		return new ColumnHeader(getDriver(), elt);
	}
}
