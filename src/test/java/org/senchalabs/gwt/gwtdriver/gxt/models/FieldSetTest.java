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

public class FieldSetTest extends BaseTest {

	@Override
	protected String getScenarioName() {
		return "fieldset";
	}

	@Test
	public void testSimpleHeading() {
		FieldSet panel = GwtWidget.find(FieldSet.class, driver).withHeading("boring").done();

		assert panel != null;
		assert panel.getElement().getText().contains("a boring heading");
	}

	@Test
	public void testContentsText() {
		FieldSet panel = GwtWidget.find(FieldSet.class, driver).withHeading("reused").done();

		assert panel != null;
		assert panel.getElement().getText().contains("reused heading");
	}
}