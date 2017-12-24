package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import nju.lighting.presentation.utils.TextFieldHelper;

/**
 * Created on 2017/12/10.
 * Description
 * 表格中可编辑的cell， 在这里定义cell的外观与行为
 *
 * @author 陈俊宇
 */
public class EditingCell extends TableCell<CommodityItem, String> {

    private JFXTextField textField;
    String type;
    ValidatorBase validator;

    public EditingCell(String type){
        this.type=type;
        switch (type){
            case "int":
                validator=new NumberValidator();break;
            case "double":
                validator=new DoubleValidator();break;
            default:
                validator=new RequiredFieldValidator();break;
        }
    }

    @Override
    public void startEdit() {
        System.out.println("start");
        if (!isEmpty()) {
            CommodityItem commodityItem = getTableView().getItems().get(getIndex());
            if (!commodityItem.isGift()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }

        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem() + "");
        setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            CommodityItem commodityItem = getTableView().getItems().get(getIndex());
            if (isEditing() && !commodityItem.isGift()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new JFXTextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);


        textField.getValidators().add(validator);


        ChangeListener<? super Boolean> changeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (textField.validate())
                        commitEdit(textField.getText());
                    else
                        cancelEdit();
                }
            }
        };

        textField.focusedProperty().addListener(changeListener);

        textField.setOnKeyPressed((ke) -> {
            if (ke.getCode().equals(KeyCode.ESCAPE)) {
                // textField.focusedProperty().removeListener(changeListener);
                cancelEdit();
            } else if (ke.getCode().equals(KeyCode.ENTER))
                getTableView().requestFocus();
        });
    }

    @Override
    public void commitEdit(String item) {

        if (isEditing()) {
            super.commitEdit(item);
        } else {
            final TableView table = getTableView();

            if (table != null) {
                TablePosition position = new TablePosition(getTableView(), getTableRow().getIndex(), getTableColumn());
                TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(table, position, TableColumn.editCommitEvent(), item);
                Event.fireEvent(getTableColumn(), editEvent);
            }
            updateItem(item, false);
            if (table != null) {
                table.edit(-1, null);
            }

        }
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

}


