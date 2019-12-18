package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.StatusDto;
import com.geekbrains.gwt.common.UserDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;


public class FilterTableWidget extends Composite {
    private ItemsClient client;

    @UiField
    TextBox titleFilter;

    @UiField
    ListBox statusFilter;

    @UiField
    ListBox ownerFilter;

    @UiField
    ListBox executerFilter;

    private ItemsTableWidget itemsTableWidget;

    @UiTemplate("FilterTable.ui.xml")
    interface FilterTableBinder extends UiBinder<Widget, FilterTableWidget> {
    }

    private static FilterTableWidget.FilterTableBinder uiBinder = GWT.create(FilterTableWidget.FilterTableBinder.class);

    public FilterTableWidget(ItemsTableWidget itemsTableWidget) {
        this.client = GWT.create(ItemsClient.class);
        this.initWidget(uiBinder.createAndBindUi(this));
        this.itemsTableWidget = itemsTableWidget;
        loadStatuses();
        loadUsers();
    }

    @UiHandler("btnFilter")
    public void submitClick(ClickEvent event) {
        itemsTableWidget.refresh(titleFilter.getValue(),
                Long.parseLong(ownerFilter.getSelectedValue()),
                Long.parseLong(executerFilter.getSelectedValue()),
                Long.parseLong(statusFilter.getSelectedValue())
        );
    }

    public void loadStatuses() {
        client.getAllStatuses(new MethodCallback<List<StatusDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список статусов: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<StatusDto> statuses) {
                statusFilter.addItem("", "");
                for (int i = 0; i < statuses.size(); i++) {
                    statusFilter.addItem(statuses.get(i).getName(), statuses.get(i).getId().toString());
                }
            }
        });
    }

    public void loadUsers() {
        client.getAllUsers(new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список пользователей: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<UserDto> users) {
                ownerFilter.addItem("", "");
                executerFilter.addItem("", "");
                for (int i = 0; i < users.size(); i++) {
                    ownerFilter.addItem(users.get(i).getName(), users.get(i).getId().toString());
                    executerFilter.addItem(users.get(i).getName(), users.get(i).getId().toString());
                }
            }
        });
    }
}
