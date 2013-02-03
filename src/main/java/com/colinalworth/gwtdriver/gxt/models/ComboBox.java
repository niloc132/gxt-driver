package com.colinalworth.gwtdriver.gxt.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.colinalworth.gwtdriver.invoke.ClientMethods;
import com.colinalworth.gwtdriver.invoke.ClientMethodsFactory;

public class ComboBox extends Field {
	private final ComboBoxMethods methods = ClientMethodsFactory.create(ComboBoxMethods.class, getDriver());

	public ComboBox(WebDriver driver, WebElement element) {
		super(driver, element);
	}

	public void clickTrigger() {
		methods.clickTrigger(getElement());
	}
	
	public ListView getListView() {
		return null;
	}

	public interface ComboBoxMethods extends ClientMethods {
		/**
		 * Not a real click, but forces the combo box to expand.
		 * @param parent
		 */
		void clickTrigger(WebElement parent);
	}

}
