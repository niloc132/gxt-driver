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
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ToStringValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.theme.blue.client.tabs.BluePlainTabPanelBottomAppearance;
import com.sencha.gxt.theme.gray.client.tabs.GrayPlainTabPanelBottomAppearance;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.tree.Tree;

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
			case "tree":
				this.@org.senchalabs.gwt.gwtdriver.gxt.models.client.App::tree()();
				break;
			case "tabPanel":
				this.@org.senchalabs.gwt.gwtdriver.gxt.models.client.App::tabPanel()();
				break;
			case "combo":
				this.@org.senchalabs.gwt.gwtdriver.gxt.models.client.App::combo()();
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
	private void tree() {
		TreeStore<String> store = new TreeStore<String>(new ModelKeyProvider<String>() {
			@Override
			public String getKey(String item) {
				return item;
			}
		});
		store.add("root");
		store.add("root", "foo");
		store.add("foo", "bar");
		store.add("root", "baz");
		store.add("other root");

		Tree<String, String> tree = new Tree<String, String>(store, new ToStringValueProvider<String>());

		tree.setPixelSize(1000, 1000);

		RootPanel.get().add(tree);
	}
	private void tabPanel() {
		//testing several panel setups to be sure that different structure doesn't affect the finder
		TabPanel panel1 = new TabPanel();
		appendTabPanel(panel1, "First");

		TabPanel panel2 = new PlainTabPanel();
		appendTabPanel(panel2, "Second");

		TabPanel panel3 = new TabPanel(new BluePlainTabPanelBottomAppearance());
		appendTabPanel(panel3, "Third");

		TabPanel panel4 = new PlainTabPanel(new GrayPlainTabPanelBottomAppearance());
		appendTabPanel(panel4, "Fourth");
	}

	private void appendTabPanel(TabPanel panel, String text) {
		panel.setCloseContextMenu(true);
		SelectHandler error = new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				com.google.gwt.user.client.Window.alert("Button was clicked, shouldn't have been");
			}
		};
		panel.add(new TextButton(text, error), new TabItemConfig(text + " plain", true));
		TabItemConfig config1 = new TabItemConfig();
		config1.setClosable(true);

		// Bug in GXT 3.0.1
		//config1.setHTML(new SafeHtmlBuilder().appendHtmlConstant("<b>").appendEscaped(text + " bold").appendHtmlConstant("</b>").toSafeHtml());
		config1.setHTML(SafeHtmlUtils.fromString(text + " bold"));
		panel.add(new TextButton("bold", error), config1);
		TabItemConfig config2 = new TabItemConfig();
		config2.setClosable(true);
//		config2.setHTML(new SafeHtmlBuilder().appendHtmlConstant("<i><span><span>").appendEscaped(text + " italic").appendHtmlConstant("</span></span></i>").toSafeHtml());
		config2.setHTML(SafeHtmlUtils.fromString(text + " italic"));
		panel.add(new TextButton("italic", error), config2);
		panel.setPixelSize(400, 200);
		RootPanel.get().add(panel);
	}

	private void combo() {
		ComboBox<String> c = new ComboBox<String>(new ListStore<String>(new ModelKeyProvider<String>() {
			@Override
			public String getKey(String item) {
				return item;
			}
		}), new LabelProvider<String>() {
			@Override
			public String getLabel(String item) {
				return item;
			}
		});
		c.getStore().add("One");
		c.getStore().add("Two");
		c.getStore().add("Three");
		c.getStore().add("Four");

		RootPanel.get().add(new FieldLabel(c, "ComboBox"));
	}
}
