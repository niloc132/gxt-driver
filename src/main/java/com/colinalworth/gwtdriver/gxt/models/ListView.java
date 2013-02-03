package com.colinalworth.gwtdriver.gxt.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.colinalworth.gwtdriver.models.GwtWidget;
import com.colinalworth.gwtdriver.models.GwtWidgetFinder;

public class ListView extends GwtWidget<GwtWidgetFinder<ListView>> {

	public ListView(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public void clickItemWithText(String text) {
		//TODO escape
		getElement().findElement(By.xpath(".//*[contains(text(), '"+text+"')]")).click();
	}
}
