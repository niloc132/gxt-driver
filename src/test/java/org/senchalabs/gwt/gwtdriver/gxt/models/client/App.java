package org.senchalabs.gwt.gwtdriver.gxt.models.client;

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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.widget.core.client.Window;

/**
 *
 */
public class App implements EntryPoint {
	public void onModuleLoad() {
	    start(com.google.gwt.user.client.Window.Location.getQueryString().substring(1));
	}
	public native void start(String key) /*-{
	    switch (key) {
			case "window":
                this.@org.senchalabs.gwt.gwtdriver.gxt.models.client.App::window()();
			break;
            default:
                this.@org.senchalabs.gwt.gwtdriver.gxt.models.client.App::error(Ljava/lang/String;)(key);
		}
	}-*/;
	private void error(String key) {
		com.google.gwt.user.client.Window.alert("Could not parse key " + key);
	}
	private void window() {
		Window a = new Window();
		a.setHeadingText("Test Window A");
		a.setTitleCollapse(true);
		a.setCollapsible(true);
		a.setWidget(new Label("Contents"));

		Window b = new Window();
		b.setHeadingText("Another Test Window");
		b.setTitleCollapse(false);
		b.setCollapsible(true);
		b.setWidget(new Label("Contents"));

		Window c = new Window();
		c.setHeadingText("Bottom Window for Testing");
		c.setCollapsible(false);
		c.setWidget(new Label("Contents"));

		//open in reverse so A is above B is above C
		c.show();
		c.setPagePosition(0,0);
		c.setPixelSize(200, 200);

		b.show();
		b.setPagePosition(0, 150);
		b.setPixelSize(200, 200);

		a.show();
		a.setPagePosition(150, 0);
		a.setPixelSize(200, 200);
	}
}
