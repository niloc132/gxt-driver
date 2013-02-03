package com.colinalworth.gwtdriver.gxt.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.colinalworth.gwtdriver.by.ByNearestWidget;
import com.colinalworth.gwtdriver.by.CheatingByChained;
import com.colinalworth.gwtdriver.gxt.models.Button.ButtonFinder;
import com.colinalworth.gwtdriver.models.GwtWidget;
import com.colinalworth.gwtdriver.models.GwtWidgetFinder;
import com.sencha.gxt.widget.core.client.button.CellButtonBase;

public class Button extends GwtWidget<ButtonFinder> {

	public Button(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public void click() {
		getElement().findElement(By.xpath("*")).click();
	}

	public String getText() {
		return getElement().getText();
	}

	public static class ButtonFinder extends GwtWidgetFinder<Button> {
		private String text;
		public ButtonFinder withText(String text) {
			this.text = text;
			return this;
		}
		@Override
		public ButtonFinder withDriver(WebDriver driver) {
			return (ButtonFinder) super.withDriver(driver);
		}
		@Override
		public Button done() {
			if (text != null) {
				String escaped = text;
				withElement(driver.findElement(new CheatingByChained(By.xpath("//*[contains(text(),'"+escaped+"')]"), new ByNearestWidget(driver, CellButtonBase.class))));
			}
			return new Button(driver, elt);
		}
	}
}
