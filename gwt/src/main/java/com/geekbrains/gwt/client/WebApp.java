package com.geekbrains.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.*;

public class WebApp implements EntryPoint {
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8189/gwt-rest/api/v1");
        ItemsTableWidget itemsTableWidget = new ItemsTableWidget();
        VerticalPanel verticalPanel = new VerticalPanel();
        AddItemFormWidget addItemFormWidget = new AddItemFormWidget(itemsTableWidget);
        verticalPanel.add(addItemFormWidget);
        FilterTableWidget filterTableWidget = new FilterTableWidget(itemsTableWidget);
        verticalPanel.add(filterTableWidget);
        itemsTableWidget.setAddItemFormWidget(addItemFormWidget);
        verticalPanel.add(itemsTableWidget);
        //RootPanel.get().add(verticalPanel);

        TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Style.Unit.EM);
        tabPanel.setAnimationDuration(100);
        tabPanel.getElement().getStyle().setMarginBottom(10.0, Style.Unit.PX);

        LoginForm loginForm = new LoginForm(tabPanel, itemsTableWidget, filterTableWidget, addItemFormWidget);
        tabPanel.add(loginForm, "Login");

        tabPanel.add(verticalPanel, "Main Page");
        tabPanel.setHeight("800px");

        tabPanel.selectTab(0);
        tabPanel.ensureDebugId("cwTabPanel");
        tabPanel.getTabWidget(0).setVisible(false);
        tabPanel.getTabWidget(1).setVisible(false);

        RootPanel.get().add(tabPanel);
    }
}