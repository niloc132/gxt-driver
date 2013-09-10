package org.senchalabs.gwt.gwtdriver.gxt.client;

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

import com.google.gwt.user.client.DOM;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import org.senchalabs.gwt.gwtdriver.client.SeleniumExporter.Method;
import org.senchalabs.gwt.gwtdriver.client.SeleniumExporter.MethodsFor;
import org.senchalabs.gwt.gwtdriver.gxt.models.ComboBox.ComboBoxMethods;

@MethodsFor(ComboBoxMethods.class)
public class ComboBoxClientMethods {
	@Method("clickTrigger")
	public void clickTrigger(XElement parent) {
		((ComboBox<?>)DOM.getEventListener(parent)).expand();
	}
}
