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
import org.senchalabs.gwt.gwtdriver.gxt.models.Panel.PanelFinder;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

import com.sencha.gxt.widget.core.client.ContentPanel;

public class Panel extends GwtWidget<PanelFinder> {

	public Panel(WebDriver driver, WebElement element) {
		super(driver, element);
	}
	
	public WebElement getHeaderElement() {
		return getElement().findElement(new FasterByChained(By.xpath(".//*"), 
				new ByWidget(getDriver(), com.sencha.gxt.widget.core.client.Header.class)));
	}

	public boolean isCollapsed() {
		return getHeaderElement().getSize().getHeight() == getElement().getSize().getHeight();
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
				String escapedString = escapeToString(heading);
				elt = elt.findElement(
						new FasterByChained(By.xpath(".//*[contains(text(), "+escapedString+")]"),
						new ByNearestWidget(driver, ContentPanel.class)));
			}
			return new Panel(driver, elt);
		}
	}
}
