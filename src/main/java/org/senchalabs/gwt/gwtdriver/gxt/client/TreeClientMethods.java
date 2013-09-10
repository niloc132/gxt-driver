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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import org.senchalabs.gwt.gwtdriver.client.SeleniumExporter.Method;
import org.senchalabs.gwt.gwtdriver.client.SeleniumExporter.MethodsFor;
import org.senchalabs.gwt.gwtdriver.gxt.models.Tree.TreeMethods;

@MethodsFor(TreeMethods.class)
public class TreeClientMethods {

	@Method("getNodeElement")
	public <T> Element getNodeElement(Element widget, Element target) {
		Tree<T, ?> tree = getTree(widget);
		return tree.getView().getElementContainer(getNode(tree, target));
	}

	@Method("getRootChildren")
	public JsArray<XElement> getRootChildren(Element widget) {
		Tree<Object, ?> tree = getTree(widget);
		JsArray<XElement> children = JsArray.createArray().cast();
		for (Object child : tree.getStore().getRootItems()) {
			children.push(tree.getView().getElementContainer(tree.findNode(child)));
		}
		return children;
	}

	@Method("getChildren")
	public JsArray<XElement> getChildren(Element widget, Element target) {
		Tree<Object, ?> tree = getTree(widget);
		JsArray<XElement> children = JsArray.createArray().cast();

		TreeNode<Object> parentNode = getNode(tree, target);
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

	@Method("getParent")
	public Element getParent(Element widget, Element target) {
		Tree<Object, ?> tree = getTree(widget);
		Object o = tree.getStore().getParent(getNode(tree, target).getModel());
		return tree.getView().getElementContainer(tree.findNode(o));
	}

	@Method("isExpanded")
	public boolean isExpanded(Element widget, Element target) {
		return getNode(getTree(widget), target).isExpanded();
	}

	@Method("getExpandElement")
	public Element getExpandElement(Element widget, Element target) {
		Tree<Object, ?> tree = getTree(widget);
		return tree.getView().getJointElement(getNode(tree, target)).cast();
	}

	@Method("getCheckElement")
	public Element getCheckElement(Element widget, Element target) {
		Tree<Object, ?> tree = getTree(widget);
		return tree.getView().getCheckElement(getNode(tree, target)).cast();
	}

	@Method("isLoading")
	public boolean isLoading(Element widget, Element target) {
		return getNode(getTree(widget), target).isLoading();
	}

	private <T> Tree<T, ?> getTree(Element elt) {
		return (Tree<T, ?>) DOM.getEventListener(elt);
	};
	private <T> TreeNode<T> getNode(Tree<T,?> tree, JavaScriptObject elt) {
		return tree.findNode(elt.<Element>cast());
	}
}
