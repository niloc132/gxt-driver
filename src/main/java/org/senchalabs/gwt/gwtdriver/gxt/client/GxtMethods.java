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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Element;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import org.senchalabs.gwt.gwtdriver.client.SeleniumExporter;
import org.senchalabs.gwt.gwtdriver.client.SeleniumExporter.Function;

public class GxtMethods implements EntryPoint {
	public void onModuleLoad() {
		SeleniumExporter.registerFunction("clickTrigger", new Function() {
			public native Object apply(JsArray<?> args) /*-{
				return args[0].__listener
						.@com.sencha.gxt.widget.core.client.form.ComboBox::expand()();
			}-*/;
		});
		SeleniumExporter.registerFunction("getNodeElement", new Function() {
			@Override
			public Object apply(JsArray<?> args) {
				Tree<Object, ?> tree = getTree(args);
				//not actually right, this should change in 3.1 to use view.getElement
				return tree.getView().getElementContainer(getNode(tree, args.get(1)));
			}
		});
		SeleniumExporter.registerFunction("getRootChildren", new Function() {
			@Override
			public Object apply(JsArray<?> args) {
				Tree<Object, ?> tree = getTree(args);
				JsArray<XElement> children = JsArray.createArray().cast();
				for (Object child : tree.getStore().getRootItems()) {
					children.push(tree.getView().getElementContainer(tree.findNode(child)));
				}
				return children;
			}
		});
		SeleniumExporter.registerFunction("getChildren", new Function() {
			@Override
			public Object apply(JsArray<?> args) {
				Tree<Object, ?> tree = getTree(args);
				JsArray<XElement> children = JsArray.createArray().cast();

				TreeNode<Object> parentNode = getNode(tree, args.get(1));
				if (parentNode.isExpanded()) {
					for (Object child : tree.getStore().getChildren(parentNode.getModel())) {
						TreeNode<Object> node = tree.findNode(child);
						if (node != null)  {
							children.push(tree.getView().getElementContainer(node));
						}
					}
				}
				return children;
			}
		});
		SeleniumExporter.registerFunction("getParent", new Function() {
			@Override
			public Object apply(JsArray <?> args) {
				Tree<Object, ?> tree = getTree(args);
				Object o = tree.getStore().getParent(getNode(tree, args.get(1)).getModel());
				return tree.getView().getElementContainer(tree.findNode(o));
			}
		});
		SeleniumExporter.registerFunction("isExpanded", new Function() {
			@Override
			public Object apply(JsArray<?> args) {
				Tree<Object, ?> tree = getTree(args);
				return "" + getNode(tree, args.get(1)).isExpanded();
			}
		});
		SeleniumExporter.registerFunction("getExpandElement", new Function() {
			@Override
			public Object apply(JsArray<?> args) {
				Tree<Object, ?> tree = getTree(args);
				return tree.getView().getJointElement(getNode(tree, args.get(1)));
			}
		});
		SeleniumExporter.registerFunction("getCheckElement", new Function() {
			@Override
			public Object apply(JsArray<?> args) {
				Tree<Object, ?> tree = getTree(args);
				return tree.getView().getCheckElement(getNode(tree, args.get(1)));
			}
		});
		SeleniumExporter.registerFunction("isLoading", new Function() {
			@Override
			public Object apply(JsArray<?> args) {
				return "" + getNode(getTree(args), args.get(1)).isLoading();
			}
		});

	}
	private native <T> Tree<T, ?> getTree(JsArray<?> args) /*-{
		return args[0].__listener;
	}-*/;
	private <T> TreeNode<T> getNode(Tree<T,?> tree, JavaScriptObject elt) {
		return tree.findNode(elt.<Element>cast());
	}
}
