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

import org.senchalabs.gwt.gwtdriver.client.SeleniumExporter;
import org.senchalabs.gwt.gwtdriver.client.SeleniumExporter.Function;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;

public class GxtMethods implements EntryPoint {
	public void onModuleLoad() {
		SeleniumExporter.registerFunction("clickTrigger", new Function() {
			public native Object apply(JsArray<?> args) /*-{
				return args[0].__listener
						.@com.sencha.gxt.widget.core.client.form.ComboBox::expand()();
			}-*/;
		});
	}
}
