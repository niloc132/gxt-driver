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
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

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
			case "menubar":
				this.@org.senchalabs.gwt.gwtdriver.gxt.models.client.App::menubar()();
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

	private void menubar() {
		MenuBar menuBar = new MenuBar();
		MenuBarItem file = new MenuBarItem("File");
		file.setMenu(new Menu());
		MenuItem newItem = new MenuItem("New");
		newItem.setSubMenu(new Menu());
		newItem.getSubMenu().add(new MenuItem("Project"));
		newItem.getSubMenu().add(new MenuItem("Object"));
		newItem.getSubMenu().add(new MenuItem("Database"));
		file.getMenu().add(newItem);

		MenuItem open = new MenuItem("Open...");
		file.getMenu().add(open);

		menuBar.add(file);

		MenuBarItem edit = new MenuBarItem("Edit");
		edit.setMenu(new Menu());
		edit.getMenu().add(new MenuItem("Copy"));
		edit.getMenu().add(new MenuItem("Paste"));

		MenuItem find = new MenuItem("Find");
		find.setSubMenu(new Menu());
		find.getSubMenu().add(new MenuItem("Find In Project"));
		find.getSubMenu().add(new MenuItem("Find In File"));

		edit.getMenu().add(find);

		menuBar.add(edit);


		RootPanel.get().add(menuBar);
		menuBar.setWidth(500);
	}
}
