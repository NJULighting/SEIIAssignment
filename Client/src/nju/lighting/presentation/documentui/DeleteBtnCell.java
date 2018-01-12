package nju.lighting.presentation.documentui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import nju.lighting.presentation.utils.ImageViewHelper;

/**
 * Created on 2017/12/10.
 * Description
 * 表格中的按钮，在这里定义按钮的外观与行为
 * @author 陈俊宇
 */

public class DeleteBtnCell<T, S> extends TableCell<T, S> {
    private final Button cellButton = new Button("", ImageViewHelper.delete());


    public DeleteBtnCell() {


        cellButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                TableView tableView = getTableView();
                tableView.getItems().remove(getTableRow().getIndex());
        }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(S t, boolean empty) {

        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(cellButton);
            setText(null);

        } else {
            setGraphic(null);
            setText(null);
        }

    }
}


