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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;

public class TabPanelTest extends BaseTest {
	@Override
	protected String getScenarioName() {
		return "tabPanel";
	}

	@Test
	public void findFourTabPanels() {
		for (String key : new String[]{"First", "Second", "Third", "Fourth"}) {
			TabPanel panel = GwtWidget.find(TabPanel.class, driver).withItem(key + " plain").done();
			assert panel != null;

			panel.clickTabWithText(key + " bold");
			Button b = panel.find(Button.class).withText("bold").done();
			assert b != null;
			assert b.getElement().isDisplayed();

			panel.clickTabWithText(key + " italic");
			Button i = panel.find(Button.class).withText("italic").done();
			assert i != null;
			assert i.getElement().isDisplayed();
		}
	}

	@Test
	public void testContextMenu() {
		for (String key : new String[]{"First", "Second", "Third", "Fourth"}) {
			TabPanel panel = GwtWidget.find(TabPanel.class, driver).withItem(key + " plain").done();

			panel.clickTabWithText(key + " italic");
			Button i = panel.find(Button.class).withText("italic").done();
			assert i != null;
			assert i.getElement().isDisplayed();

			panel.rightClickTabWithText(key + " bold").click("Close all other");

			try {
				i.getElement().isDisplayed();
				Assert.fail();
			} catch (StaleElementReferenceException e) {
				//expected error, tab is gone
			}

			try {
				panel.clickTabWithText(key + " italic");
				Assert.fail();
			} catch (NoSuchElementException e) {
				//expected error, tab is gone
			}

			Button b = panel.find(Button.class).withText("bold").done();
			assert b != null;
			assert b.getElement().isDisplayed();

		}
	}

	@Test
	public void clickTextAlsoInsideTab() {
		for (String key : new String[]{"First", "Second", "Third", "Fourth"}) {
			TabPanel panel = GwtWidget.find(TabPanel.class, driver).withItem(key + " plain").done();

			panel.clickTabWithText("bold");
		}
	}
}
