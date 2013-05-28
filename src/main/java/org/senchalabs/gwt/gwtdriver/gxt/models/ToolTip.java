package org.senchalabs.gwt.gwtdriver.gxt.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.senchalabs.gwt.gwtdriver.by.ByNearestWidget;
import org.senchalabs.gwt.gwtdriver.by.ByWidget;
import org.senchalabs.gwt.gwtdriver.by.FasterByChained;
import org.senchalabs.gwt.gwtdriver.gxt.models.ToolTip.ToolTipFinder;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

import com.sencha.gxt.widget.core.client.tips.Tip;

@ForWidget(Tip.class)
public class ToolTip extends GwtWidget<ToolTipFinder> {

	public ToolTip(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public static class ToolTipFinder extends GwtWidgetFinder<ToolTip> {
		private boolean atTop = false;
		private String text;
		public ToolTipFinder withText(String text) {
			this.text = text;
			return this;
		}
		public ToolTipFinder atTop() {
			atTop = true;
			return this;
		}
		@Override
		public ToolTip done() {
			if (atTop) {
				List<ToolTip> allTooltips = findAllActive(driver);
				Collections.sort(allTooltips, new Comparator<ToolTip>() {
					@Override
					public int compare(ToolTip o1, ToolTip o2) {
						return Integer.parseInt(o2.getElement().getCssValue("z-index")) - Integer.parseInt(o1.getElement().getCssValue("z-index"));
					}
				});
			
				return allTooltips.get(0);
			} else if (text != null) {
				elt = driver.findElement(new FasterByChained(By.xpath("//body/*"),
						new ByWidget(driver, Tip.class),
						By.xpath(".//*[contains(text(), "+escapeToString(text)+")]"),
						new ByNearestWidget(driver, Tip.class)));
			}
			return new ToolTip(driver, elt);
		}
	}

	public static List<ToolTip> findAllActive(WebDriver driver) {
		List<WebElement> elts = driver.findElements(new ByChained(By.xpath("//body/*"),
				new ByWidget(driver, Tip.class)));
		List<ToolTip> tooltips = new ArrayList<ToolTip>();
		for (WebElement elt : elts) {
			tooltips.add(new ToolTip(driver, elt));
		}
		return tooltips;
	}
}
