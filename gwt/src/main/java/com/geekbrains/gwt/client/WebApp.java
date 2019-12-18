package com.geekbrains.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.*;

public class WebApp implements EntryPoint {
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8189/gwt-rest/api/v1");
        ItemsTableWidget itemsTableWidget = new ItemsTableWidget();
        VerticalPanel verticalPanel = new VerticalPanel();
        AddItemFormWidget addItemFormWidget = new AddItemFormWidget(itemsTableWidget);
        verticalPanel.add(addItemFormWidget);
        verticalPanel.add(new FilterTableWidget(itemsTableWidget));
        itemsTableWidget.setAddItemFormWidget(addItemFormWidget);
        verticalPanel.add(itemsTableWidget);
        RootPanel.get().add(verticalPanel);
    }
}