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

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.senchalabs.gwt.gwtdriver.by.ByWidget;
import org.senchalabs.gwt.gwtdriver.gxt.models.Tree.Item;

public class TreeDndTest extends BaseTest {
	@Override
	protected String getScenarioName() {
		return "treednd";
	}

	@Test
	public void testDragAndDrop() {
		Tree t = new Tree(driver, driver.findElement(new ByChained(By.xpath("//body/*"), new ByWidget(driver, com.sencha.gxt.widget.core.client.tree.Tree.class))));

		Item root = t.findItemWithText("root");
		root.toggleExpand();
		Assert.assertEquals(2, root.getChildren().size());

		Item foo = root.getChildren().get(0);
		foo.toggleExpand();

		Item bar = foo.getChildren().get(0);
		foo.getElement();
		Actions a = new Actions(driver);
		a.clickAndHold(bar.getElement());
		a.moveToElement(root.getElement());
		a.release();

		a.perform();

		Assert.assertEquals(3, root.getChildren().size());

	}
	@Test
	public void testDragExpandAndDrop() throws InterruptedException {
		Tree t = new Tree(driver, driver.findElement(new ByChained(By.xpath("//body/*"), new ByWidget(driver, com.sencha.gxt.widget.core.client.tree.Tree.class))));

		Item root = t.findItemWithText("root");
		Item other = t.getRootChildren().get(1);

		//drag, still holding
		Actions a = new Actions(driver);
		a.clickAndHold(other.getElement());
		a.moveToElement(root.getElement());
		a.perform();

		//wait until expanded (800ms by default, wait 1 second)
		Thread.sleep(1000);

		Assert.assertEquals(2, root.getChildren().size());
		Item foo = root.getChildren().get(0);

		a = new Actions(driver);
		a.moveToElement(foo.getElement());
		a.release();
		a.perform();

		Assert.assertEquals(1, t.getRootChildren().size());
		Assert.assertEquals(false, foo.isExpanded());
		Assert.assertEquals(2, root.getChildren().size());

		foo.toggleExpand();
		Assert.assertEquals(2, foo.getChildren().size());
	}
}
