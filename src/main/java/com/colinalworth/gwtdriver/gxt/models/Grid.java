package com.colinalworth.gwtdriver.gxt.models;

import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.pagefactory.ByChained;

import com.colinalworth.gwtdriver.by.ByNearestWidget;
import com.colinalworth.gwtdriver.by.ByWidget;
import com.colinalworth.gwtdriver.by.FasterByChained;
import com.colinalworth.gwtdriver.models.GwtWidget;
import com.colinalworth.gwtdriver.models.GwtWidgetFinder;

public class Grid extends GwtWidget<GwtWidgetFinder<Grid>> {

	public Grid(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public static class ColumnHeader extends GwtWidget<GwtWidgetFinder<ColumnHeader>> {
		public ColumnHeader(WebDriver driver, WebElement element) {
			super(driver, element);
		}
		public void click() {
			getElement().click();
		}
		public Menu clickContextMenu() {
			Coordinates loc = ((Locatable)getElement()).getCoordinates();
			((HasInputDevices)getDriver()).getMouse().mouseMove(loc);
			getElement().findElement(By.tagName("a")).click();
			return new Menu.MenuFinder().atTop().withDriver(getDriver()).done();
		}
	}

	public ColumnHeader getColumnHeaderWithHeading(String headingText) {
		WebElement elt = getElement().findElement(new FasterByChained(
				By.xpath(".//div[a][img]//*[contains(text(), '" + headingText + "')]"), 
				new ByNearestWidget(getDriver(), com.sencha.gxt.widget.core.client.grid.ColumnHeader.Head.class)));
		return new ColumnHeader(getDriver(), elt);
	}

	public ColumnHeader getColumnHeaderWithIndex(int index) {
		WebElement elt = getElement().findElements(new ByChained(By.xpath(".//div[a][img]"), new ByWidget(getDriver(), com.sencha.gxt.widget.core.client.grid.ColumnHeader.Head.class))).get(index);
		return new ColumnHeader(getDriver(), elt);
	}
}
