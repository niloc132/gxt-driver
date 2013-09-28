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

import org.junit.Test;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget;

public class InfoPopupTest extends BaseTest {
	@Override
	protected String getScenarioName() {
		return "info";
	}

	@Test
	public void testTopPopup() throws InterruptedException {
		//wait one second, since they all go off within 100ms of eachother at startup
		Thread.sleep(1000);
		InfoPopup info = GwtWidget.find(InfoPopup.class, driver).atTop().done();

		assert info.getText().contains("Message #5");
	}

	@Test
	public void testByName() {
		//doing a waitfor since it might take a moment
		InfoPopup info = GwtWidget.find(InfoPopup.class, driver).withText("First!").waitFor();
		assert info.getText().contains("Initial message");


		InfoPopup info2 = GwtWidget.find(InfoPopup.class, driver).withText("#2").waitFor();
		assert info2.getText().contains("test2");
	}
}
