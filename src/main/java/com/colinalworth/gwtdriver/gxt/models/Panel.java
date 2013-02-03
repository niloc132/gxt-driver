package com.colinalworth.gwtdriver.gxt.models;

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
