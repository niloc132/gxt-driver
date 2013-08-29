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
import org.openqa.selenium.support.pagefactory.ByChained;
import org.senchalabs.gwt.gwtdriver.by.ByWidget;
import org.senchalabs.gwt.gwtdriver.gxt.models.Tree.Item;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 8/29/13
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class TreeTest extends BaseTest {

	@Override
	protected String getScenarioName() {
		return "tree";
	}

	@Test
	public void testRootNodes() {
		Tree t = new Tree(driver, driver.findElement(new ByChained(By.xpath("//body/*"), new ByWidget(driver, com.sencha.gxt.widget.core.client.tree.Tree.class))));

		Assert.assertEquals(2, t.getRootChildren().size());

		Item root = t.findItemWithText("root");
		Assert.assertEquals(false, root.isExpanded());
		Assert.assertEquals(0, root.getChildren().size());
		root.toggleExpand();
		Assert.assertEquals(2, root.getChildren().size());
	}
}
