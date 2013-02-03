package com.colinalworth.gwtdriver.gxt.models;

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
