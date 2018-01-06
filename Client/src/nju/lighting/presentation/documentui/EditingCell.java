package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXTextField;
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
 * @author 陈俊宇
 */
public class EditingCell<T, S> extends TableCell<T, S> {

    String type;
    ValidatorBase validator;
    private JFXTextField textField;

    public EditingCell(String type) {
        this.type = type;

    }

    @Override
    public void startEdit() {
        System.out.println("start Edit");
        if (!isEmpty()) {
            // CommodityItem commodityItem = getTableView().getItems().get(getIndex());
//            if (!commodityItem.isGift()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
            // }

        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem() + "");
        setGraphic(null);
    }

    @Override
    public void updateItem(S item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            //CommodityItem commodityItem = getTableView().getItems().get(getIndex());
            //if (isEditing() && !commodityItem.isGift()) {
            if (isEditing()) {
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

        switch (type) {
            case "int":
                TextFieldHelper.addNameValidator(textField);
                break;
            case "double":
                TextFieldHelper.addDoubleValidator(textField);
                break;
            default:
                TextFieldHelper.addRequiredValidator(textField);
                break;
        }



        ChangeListener<? super Boolean> changeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (textField.validate()) {
                        if (type.equals("double"))
                            commitEdit((S) (new Double(Double.parseDouble(textField.getText()))));
                        else if (type.equals("int"))
                            commitEdit((S) (new Integer(Integer.parseInt(textField.getText()))));
                        else
                            commitEdit((S) textField.getText());
                    } else
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
    public void commitEdit(S item) {
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


