package org.senchalabs.gwt.gwtdriver.gxt.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.senchalabs.gwt.gwtdriver.by.ByNearestWidget;
import org.senchalabs.gwt.gwtdriver.by.ByWidget;
import org.senchalabs.gwt.gwtdriver.by.FasterByChained;
import org.senchalabs.gwt.gwtdriver.gxt.models.TabPanel.TabPanelFinder;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 8/29/13
 * Time: 6:38 PM
 * To change this template use File | Settings | File Templates.
 */
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
}
