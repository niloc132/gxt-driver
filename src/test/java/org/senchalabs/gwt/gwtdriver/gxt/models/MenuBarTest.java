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
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;

public class MenuBarTest extends BaseTest {
	@Override
	protected String getScenarioName() {
		return "menubar";
	}

	@Test
	public void testClick() {
		MenuBar menuBar = GwtWidget.find(MenuBar.class, driver).withItem("File").done();

		try {
			menuBar.click("None");
			Assert.fail();
		} catch (Exception ex) {
			// expected error, doesn't exist
		}

		Menu editMenu = menuBar.click("Edit");

		assert editMenu != null;
	}
	@Test
	public void testHover() throws Exception {
		MenuBar menuBar = GwtWidget.find(MenuBar.class, driver).withItem("File").done();

		Menu fileMenu = menuBar.click("File");

		//TODO hover over something else first, looks like 3.0.1 bug?
		fileMenu.mouseOver("Open...");

		try {
			fileMenu.mouseOver("Missing");
			Assert.fail();
		} catch (Exception ex) {
			// expected error, doesn't exist
		}

		Menu newMenu = fileMenu.mouseOver("New");

		assert newMenu != null;

		try {
			newMenu.click("Uh-uh");
			Assert.fail();
		} catch (Exception ex) {
			// expected error, doesn't exist
		}
		newMenu.click("Database");
	}
}
