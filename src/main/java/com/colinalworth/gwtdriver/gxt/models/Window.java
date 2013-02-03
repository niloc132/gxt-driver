package com.colinalworth.gwtdriver.gxt.models;


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
				String escaped = heading;
				elt = driver.findElement(new FasterByChained(By.xpath("//body/*"),
								new ByWidget(driver, com.sencha.gxt.widget.core.client.Window.class), 
								By.xpath(".//*[contains(text(), '" + escaped + "')]"), 
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
