package com.colinalworth.gwtdriver.gxt.models;

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


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.colinalworth.gwtdriver.by.ByNearestWidget;
import com.colinalworth.gwtdriver.by.ByWidget;
import com.colinalworth.gwtdriver.by.FasterByChained;
import com.colinalworth.gwtdriver.gxt.models.Window.WindowFinder;
import com.colinalworth.gwtdriver.models.GwtWidget;
import com.colinalworth.gwtdriver.models.GwtWidgetFinder;
import com.colinalworth.gwtdriver.models.GwtWidget.ForWidget;

@ForWidget(com.sencha.gxt.widget.core.client.Window.class)
public class Window extends GwtWidget<WindowFinder> {
	public Window(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public static class WindowFinder extends GwtWidgetFinder<Window> {
		private String heading;
		private boolean atTop = false;

		public WindowFinder withHeading(String heading) {
			this.heading = heading;
			return this;
		}
		@Override
		public WindowFinder withDriver(WebDriver driver) {
			return (WindowFinder) super.withDriver(driver);
		}
		public WindowFinder atTop() {
			atTop = true;
			return this;
		}
		@Override
		public Window done() {
			if (heading != null) {
				String escaped = escapeToString(heading);
				//TODO the first two clauses can be a ByChained, then iterate over items and see if
				//it matches the contains(), if so, just return the starting point
				elt = driver.findElement(new FasterByChained(By.xpath("//body/*"),
								new ByWidget(driver, com.sencha.gxt.widget.core.client.Window.class), 
								By.xpath(".//*[contains(text(), " + escaped + ")]"), 
								new ByNearestWidget(driver, com.sencha.gxt.widget.core.client.Window.class)));
			} else if (atTop) {
				//TODO totally untested
				List<WebElement> allWindows = driver.findElements(new ByChained(By.xpath("//body/*"),
						new ByWidget(driver, com.sencha.gxt.widget.core.client.Window.class)));
				Collections.sort(allWindows, new Comparator<WebElement>() {
					public int compare(WebElement o1, WebElement o2) {
						return Integer.parseInt(o1.getCssValue("z-index")) - Integer.parseInt(o2.getCssValue("z-index"));
					}
				});
				elt = allWindows.get(0);
			}
			return new Window(driver, elt);
		}
	}
}
