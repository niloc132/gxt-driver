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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.senchalabs.gwt.gwtdriver.invoke.ClientMethods;
import org.senchalabs.gwt.gwtdriver.invoke.ClientMethodsFactory;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;
import org.senchalabs.gwt.gwtdriver.models.GwtWidgetFinder;

/**
 */
@ForWidget(com.sencha.gxt.widget.core.client.tree.Tree.class)
public class Tree extends GwtWidget<GwtWidgetFinder<Tree>> {
	public interface TreeMethods extends ClientMethods {
		/** From any element within the widget, finds the nearest complete node. */
		WebElement getNodeElement(WebElement widget, WebElement elt);

		/** Find the root children */
		List<WebElement> getRootChildren(WebElement widget);
		/** Find the children of the given item */
		List<WebElement>getChildren(WebElement widget, WebElement item);
		/** Gets the element of the parent node of the given item */
		WebElement getParent(WebElement widget, WebElement item);

		boolean isExpanded(WebElement widget, WebElement item);
		WebElement getExpandElement(WebElement widget, WebElement item);

		WebElement getCheckElement(WebElement widget, WebElement item);
//		CheckEnum getCheck(WebElement widget, WebElement item);

		boolean isLoading(WebElement widget, WebElement item);
	}
	private final TreeMethods methods;

	public Tree(WebDriver driver, WebElement element) {
		super(driver, element);
		methods = ClientMethodsFactory.create(TreeMethods.class, driver);
	}

	public class Item {
		private final WebElement elt;
		private Item(WebElement element) {
			this.elt = element;
		}
		public String getText() {
			return elt.getText();
		}
		public WebElement getElement() {
			return elt;
		}
		public void toggleExpand() {
			methods.getExpandElement(getWidgetElement(), getElement()).click();
		}
		public boolean isExpanded() {
			return methods.isExpanded(getWidgetElement(), getElement());
		}
		public void toggleCheck() {
			methods.getCheckElement(getWidgetElement(), getElement()).click();
		}
		//TODO check enum

		public void waitForLoaded() {
			waitForLoaded(10, TimeUnit.SECONDS);
		}
		public void waitForLoaded(long duration, TimeUnit unit) {
			new FluentWait<WebDriver>(getDriver())
					.withTimeout(duration, unit)
					.ignoring(NotFoundException.class)
					.until(new Predicate<WebDriver>() {
						@Override
						public boolean apply(WebDriver input) {
							return !methods.isLoading(getWidgetElement(), getElement());
						}
					});
		}
		public List<Item> getChildren() {
			List<WebElement> childNodes = methods.getChildren(getWidgetElement(), getElement());
			List<Item> items = new ArrayList<Item>();
			for (WebElement childNode : childNodes) {
				items.add(new Item(childNode));
			}
			return items;
		}
		public boolean isLeaf() {
			//TODO slooooow
			return getChildren().isEmpty();
		}
		public Item getParent() {
			return new Item(methods.getParent(getWidgetElement(), getElement()));
		}
	}

	public Item findItemWithText(String text) {
		String escaped = escapeToString(text);
		return new Item(methods.getNodeElement(getElement(), getElement().findElement(By.xpath(".//*[contains(text(),"+escaped+")]"))));
	}

	public List<Item> getRootChildren() {
		List<WebElement> childNodes = methods.getRootChildren(getWidgetElement());
		List<Item> items = new ArrayList<Item>();
		for (WebElement child : childNodes) {
			items.add(new Item(child));
		}
		return items;
	}

	protected WebElement getWidgetElement() {
		return getElement();
	}
}
