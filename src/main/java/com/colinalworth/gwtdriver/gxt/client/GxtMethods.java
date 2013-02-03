package com.colinalworth.gwtdriver.gxt.client;

import com.colinalworth.gwtdriver.client.SeleniumExporter;
import com.colinalworth.gwtdriver.client.SeleniumExporter.Function;
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
