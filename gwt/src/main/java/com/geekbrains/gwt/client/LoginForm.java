package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.JwtAuthRequestDto;
import com.geekbrains.gwt.common.JwtAuthResponseDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class LoginForm extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox textUsername;

    @UiField
    TextBox textPassword;

    @UiTemplate("LoginForm.ui.xml")
    interface LoginFormBinder extends UiBinder<Widget, LoginForm> {
    }

    private ItemsTableWidget itemsTableWidget;
    private FilterTableWidget filterTableWidget;
    private AddItemFormWidget addItemFormWidget;
    private TabLayoutPanel tabPanel;

    private static LoginForm.LoginFormBinder uiBinder = GWT.create(LoginForm.LoginFormBinder.class);

    public LoginForm(TabLayoutPanel tabPanel, ItemsTableWidget itemsTableWidget, FilterTableWidget filterTableWidget, AddItemFormWidget addItemFormWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.form.setAction("localhost:8189/market/authenticateTheUser");
        this.itemsTableWidget = itemsTableWidget;
        this.filterTableWidget = filterTableWidget;
        this.addItemFormWidget = addItemFormWidget;
        this.tabPanel = tabPanel;
    }

    @UiHandler("form")
    public void onSubmit(FormPanel.SubmitEvent event) {
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        Window.alert(event.getResults());
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        JwtAuthRequestDto jwtAuthRequestDto = new JwtAuthRequestDto(textUsername.getValue(), textPassword.getValue());
        AuthClient authClient = GWT.create(AuthClient.class);
        authClient.authenticate(jwtAuthRequestDto, new MethodCallback<JwtAuthResponseDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, JwtAuthResponseDto jwtAuthResponseDto) {
                GWT.log(jwtAuthResponseDto.getToken());
                Storage.getLocalStorageIfSupported().setItem("jwt", "Bearer " +  jwtAuthResponseDto.getToken());
                addItemFormWidget.refresh();
                filterTableWidget.refresh();
                itemsTableWidget.refresh(null, null, null, null);
                tabPanel.selectTab(1);
            }
        });
    }
}