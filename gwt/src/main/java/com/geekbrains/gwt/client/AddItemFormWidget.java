package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.StatusDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.gwt.common.UserDto;
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

import java.util.List;

public class AddItemFormWidget extends Composite {
    private ItemsClient client;

    @UiField
    Hidden idText;

    @UiField
    TextBox titleText;

    @UiField
    TextBox descrText;

    @UiField
    ListBox statusSel;

    @UiField
    ListBox ownerSel;

    @UiField
    ListBox execSel;

    private ItemsTableWidget itemsTableWidget;

    @UiTemplate("AddItemForm.ui.xml")
    interface AddItemFormBinder extends UiBinder<Widget, AddItemFormWidget> {
    }

    private static AddItemFormWidget.AddItemFormBinder uiBinder = GWT.create(AddItemFormWidget.AddItemFormBinder.class);

    public AddItemFormWidget(ItemsTableWidget itemsTableWidget) {
        this.client = GWT.create(ItemsClient.class);
        this.initWidget(uiBinder.createAndBindUi(this));
        this.itemsTableWidget = itemsTableWidget;
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        TaskDto taskDto = new TaskDto();
        String id = idText.getValue();
        if (id.length() > 0) {
            taskDto.setId(Long.parseLong(idText.getValue()));
        }
        taskDto.setTitle(titleText.getValue());
        taskDto.setDescription(descrText.getValue());

        UserDto owner = new UserDto();
        owner.setId(Long.parseLong(ownerSel.getSelectedValue()));
        owner.setUsername(ownerSel.getSelectedItemText());
        taskDto.setOwner(owner);

        UserDto executer = new UserDto();
        executer.setId(Long.parseLong(execSel.getSelectedValue()));
        executer.setUsername(execSel.getSelectedItemText());
        taskDto.setExecuter(executer);

        StatusDto status = new StatusDto();
        status.setId(Long.parseLong(statusSel.getSelectedValue()));
        status.setName(statusSel.getSelectedItemText());
        taskDto.setStatus(status);
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");
        client.add(token, taskDto, new MethodCallback<Void> () {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Ошибка при создании/обновлении таска. Подробности в консоли");
            }

            @Override
            public void onSuccess(Method method, Void aVoid) {
                clearForm();
                itemsTableWidget.refresh(null, null, null, null);
            }
        });
    }

    public void clearForm() {
        idText.setValue("");
        titleText.setValue("");
        descrText.setValue("");
        statusSel.setSelectedIndex(0);
        ownerSel.setSelectedIndex(0);
        execSel.setSelectedIndex(0);
    }

    @UiHandler("btnClear")
    public void clearClick(ClickEvent event) {
        clearForm();
    }

    public void loadStatuses() {
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");
        client.getAllStatuses(token, new MethodCallback<List<StatusDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список статусов: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<StatusDto> statuses) {
                statusSel.addItem("", "");
                for (int i = 0; i < statuses.size(); i++) {
                    statusSel.addItem(statuses.get(i).getName(), statuses.get(i).getId().toString());
                }
            }
        });
    }

    public void loadUsers() {
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");
        client.getAllUsers(token, new MethodCallback<List<UserDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список пользователей: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<UserDto> users) {
                ownerSel.addItem("", "");
                execSel.addItem("", "");
                for (int i = 0; i < users.size(); i++) {
                    ownerSel.addItem(users.get(i).getUsername(), users.get(i).getId().toString());
                    execSel.addItem(users.get(i).getUsername(), users.get(i).getId().toString());
                }
            }
        });
    }

    private void setSelectedValue(ListBox lBox, String str) {
        String text = str;
        int indexToFind = -1;
        for (int i = 0; i < lBox.getItemCount(); i++) {
            if (lBox.getValue(i).equals(text)) {
                indexToFind = i;
                break;
            }
        }
        lBox.setSelectedIndex(indexToFind);
    }

    public void setForm (TaskDto dto) {
        idText.setValue(dto.getId().toString());
        titleText.setValue(dto.getTitle());
        descrText.setValue(dto.getDescription());
        this.setSelectedValue(statusSel, dto.getStatus().getId().toString());
        this.setSelectedValue(ownerSel, dto.getOwner().getId().toString());
        this.setSelectedValue(execSel, dto.getExecuter().getId().toString());
    }

    public void refresh() {
        loadStatuses();
        loadUsers();
    }
}
