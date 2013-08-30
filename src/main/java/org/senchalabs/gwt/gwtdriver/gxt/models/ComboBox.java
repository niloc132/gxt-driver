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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.senchalabs.gwt.gwtdriver.invoke.ClientMethods;
import org.senchalabs.gwt.gwtdriver.invoke.ClientMethodsFactory;
import org.senchalabs.gwt.gwtdriver.models.GwtWidget.ForWidget;

@ForWidget(com.sencha.gxt.widget.core.client.form.ComboBox.class)
public class ComboBox extends Field {
  private final ComboBoxMethods methods = ClientMethodsFactory.create(ComboBoxMethods.class, getDriver());

  public ComboBox(WebDriver driver, WebElement element) {
    super(driver, element);
  }

  public void clickTrigger() {
    methods.clickTrigger(getElement());
  }

  public boolean isExpanded() {
    return methods.isExpanded();
  }

  public ListView getListView() {
    return null;
  }

  public interface ComboBoxMethods extends ClientMethods {
    /**
     * Not a real click, but forces the combo box to expand.
     * 
     * @param parent
     */
    void clickTrigger(WebElement parent);

    boolean isExpanded();
  }

}
