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

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.colinalworth.gwtdriver.by.ByWidget;
import com.colinalworth.gwtdriver.gxt.models.Menu.MenuFinder;
import com.colinalworth.gwtdriver.models.GwtWidget;
import com.colinalworth.gwtdriver.models.GwtWidgetFinder;

public class Menu extends GwtWidget<MenuFinder> {
	public Menu(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public static class MenuFinder extends GwtWidgetFinder<Menu> {
		private int depth = 0;
		public MenuFinder atTop() {
			return atDepth(0);
		}
		public MenuFinder atDepth(int depth) {
			
			return this;
		}
		@Override
		public Menu done() {
			List<WebElement> allMenus = driver.findElements(new ByChained(By.xpath("//body/*"),
					new ByWidget(driver, com.sencha.gxt.widget.core.client.menu.Menu.class)));
			return new Menu(driver, allMenus.get(allMenus.size() - 1 - depth));
		}
	}
}
