package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.TaskDto;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

public class ItemsTableWidget extends Composite {
    @UiField
    CellTable<TaskDto> table;

    private ItemsClient client;

    @UiTemplate("ItemsTable.ui.xml")
    interface ItemsTableBinder extends UiBinder<Widget, ItemsTableWidget> {
    }

    private static ItemsTableBinder uiBinder = GWT.create(ItemsTableBinder.class);

    private AddItemFormWidget addItemFormWidget;

    public void setAddItemFormWidget(AddItemFormWidget addItemFormWidget) {
        this.addItemFormWidget = addItemFormWidget;
    }

    public ItemsTableWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        TextColumn<TaskDto> idColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto itemDto) {
                return itemDto.getId().toString();
            }
        };
        table.addColumn(idColumn, "ID");

        TextColumn<TaskDto> titleColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto itemDto) {
                return itemDto.getTitle();
            }
        };
        table.addColumn(titleColumn, "Наименование");

        TextColumn<TaskDto> ownerColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto itemDto) {
                return itemDto.getOwner();
            }
        };
        table.addColumn(ownerColumn, "Владелец");

        TextColumn<TaskDto> executerColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto itemDto) {
                return itemDto.getExecuter();
            }
        };
        table.addColumn(executerColumn, "Исполнитель");

        TextColumn<TaskDto> statusColumn = new TextColumn<TaskDto>() {
            @Override
            public String getValue(TaskDto itemDto) {
                return itemDto.getStatus();
            }
        };
        table.addColumn(statusColumn, "Статус");

        client = GWT.create(ItemsClient.class);

        Column<TaskDto, TaskDto> editColumn = new Column<TaskDto, TaskDto>(
                new ActionCell<TaskDto>("Изменить", new ActionCell.Delegate<TaskDto>() {
                    @Override
                    public void execute(TaskDto item) {
                        String token = Storage.getLocalStorageIfSupported().getItem("jwt");
                        client.getItem(token, item.getId().toString(), new MethodCallback<TaskDto>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {
                                GWT.log(throwable.toString());
                                GWT.log(throwable.getMessage());
                            }

                            @Override
                            public void onSuccess(Method method, TaskDto result) {
                                addItemFormWidget.setForm(result);
                            }
                        });
                    }
                })) {
            @Override
            public TaskDto getValue(TaskDto item) {
                return item;
            }
        };
        table.addColumn(editColumn, "");

        Column<TaskDto, TaskDto> delColumn = new Column<TaskDto, TaskDto>(
                new ActionCell<TaskDto>("Удалить", new ActionCell.Delegate<TaskDto>() {
                    @Override
                    public void execute(TaskDto item) {
                        if (Window.confirm("Подтвердите удаление")) {
                            String token = Storage.getLocalStorageIfSupported().getItem("jwt");
                            client.removeItem(token, item.getId().toString(), new MethodCallback<Void>() {
                                @Override
                                public void onFailure(Method method, Throwable throwable) {
                                    GWT.log(throwable.toString());
                                    GWT.log(throwable.getMessage());
                                }

                                @Override
                                public void onSuccess(Method method, Void result) {
                                    refresh(null, null, null, null);
                                }
                            });
                        }
                    }
                })) {
            @Override
            public TaskDto getValue(TaskDto item) {
                return item;
            }
        };
        table.addColumn(delColumn, "");

        table.setColumnWidth(idColumn, 50, Style.Unit.PX);
        table.setColumnWidth(titleColumn, 200, Style.Unit.PX);
        table.setColumnWidth(ownerColumn, 150, Style.Unit.PX);
        table.setColumnWidth(executerColumn, 150, Style.Unit.PX);
        table.setColumnWidth(statusColumn, 100, Style.Unit.PX);
        table.setColumnWidth(editColumn, 100, Style.Unit.PX);
        table.setColumnWidth(delColumn, 100, Style.Unit.PX);

        refresh(null, null, null, null);
    }

    public void refresh(String title, Long ownerId, Long executerId, Long statusId) {
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");
        if (token == null) {
            return;
        }
        client.getAllItems(token, title, ownerId, executerId, statusId, new MethodCallback<List<TaskDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список items: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<TaskDto> i) {
                GWT.log(title);
                GWT.log("Received " + i.size() + " items");
                List<TaskDto> items = new ArrayList<>();
                items.addAll(i);
                table.setRowData( items);
            }
        });
    }
}
