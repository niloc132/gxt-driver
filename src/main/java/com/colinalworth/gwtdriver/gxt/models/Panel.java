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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.colinalworth.gwtdriver.by.ByNearestWidget;
import com.colinalworth.gwtdriver.by.FasterByChained;
import com.colinalworth.gwtdriver.gxt.models.Panel.PanelFinder;
import com.colinalworth.gwtdriver.models.GwtWidget;
import com.colinalworth.gwtdriver.models.GwtWidgetFinder;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class Panel extends GwtWidget<PanelFinder> {

	public Panel(WebDriver driver, WebElement element) {
		super(driver, element);
	}
	
	//todo tools, buttons
	//todo children

	public static class PanelFinder extends GwtWidgetFinder<Panel> {
		private String heading;
		public PanelFinder withHeading(String heading) {
			this.heading = heading;
			return this;
		}
		@Override
		public Panel done() {
			if (heading != null) {
				elt = elt.findElement(
						new FasterByChained(By.xpath(".//*[contains(text(), '"+heading+"')]"),
						new ByNearestWidget(driver, ContentPanel.class)));
			}
			return new Panel(driver, elt);
		}
	}
}
