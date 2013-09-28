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

import com.sencha.gxt.widget.core.client.info.Info;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.senchalabs.gwt.gwtdriver.by.ByNearestWidget;
import org.senchalabs.gwt.gwtdriver.by.ByWidget;
import org.senchalabs.gwt.gwtdriver.by.FasterByChained;
import org.senchalabs.gwt.gwtdriver.gxt.models.InfoPopup.InfoPopupFinder;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ForWidget(Info.class)
public class InfoPopup extends GwtWidget<InfoPopupFinder> {
	public InfoPopup(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public String getText() {
		return getElement().getText();
	}

	public static class InfoPopupFinder extends GwtWidgetFinder<InfoPopup> {
		private boolean atTop = false;
		private String text;
		public InfoPopupFinder atTop() {
			atTop = true;
			return this;
		}
		public InfoPopupFinder withText(String text) {
			this.text = text;
			return this;
		}

		@Override
		public InfoPopup done() {
			WebElement elt = null;
			if (text != null) {
				String escaped = escapeToString(text);
				elt = driver.findElement(new FasterByChained(By.xpath("//body/*"),
						new ByWidget(driver, Info.class),
						By.xpath(".//*[contains(text(), "+escaped+")]"),
						new ByNearestWidget(driver, Info.class)));
			} else if (atTop) {
				List<WebElement> allInfos = driver.findElements(new ByChained(By.xpath("//body/*"),
						new ByWidget(driver, Info.class)));
				Collections.sort(allInfos, new Comparator<WebElement>() {
					public int compare(WebElement o1, WebElement o2) {
						return Integer.parseInt(o2.getCssValue("z-index")) - Integer.parseInt(o1.getCssValue("z-index"));
					}
				});
				elt = allInfos.get(0);
			}
			return new InfoPopup(driver, elt);
		}
	}
}
