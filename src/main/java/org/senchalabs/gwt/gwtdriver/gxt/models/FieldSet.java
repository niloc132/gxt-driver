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
import org.senchalabs.gwt.gwtdriver.gxt.models.FieldSet.FieldSetFinder;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

@ForWidget(com.sencha.gxt.widget.core.client.form.FieldSet.class)
public class FieldSet extends GwtWidget<FieldSetFinder> {

	public FieldSet(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public static class FieldSetFinder extends GwtWidgetFinder<FieldSet> {

		private String heading;
		public FieldSetFinder withHeading(String heading) {
			this.heading = heading;
			return this;
		}

		@Override
		public FieldSet done() {
			WebElement element = elt;
			if (heading != null) {
				String escaped = escapeToString(heading);
				element = element.findElement(new FasterByChained(
						By.xpath(".//*[contains(text(), " + escaped + ")]"),
						new ByNearestWidget(driver),
						new ByWidget(driver, com.sencha.gxt.widget.core.client.form.FieldSet.class)
				));
			}
			return new FieldSet(driver, element);
		}
	}
}
