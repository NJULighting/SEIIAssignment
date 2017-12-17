package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import nju.lighting.presentation.utils.ImageViewHelper;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

/**
 * Created on 2017/12/10.
 * Description
 *表格中的按钮，在这里定义按钮的外观与行为
 * @author 陈俊宇
 */

public class BtnCell extends TableCell<CommodityItem, Boolean> {
    final Button cellButton = new Button("",ImageViewHelper.delete());


    BtnCell() {



        cellButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                TableView tableView=getTableView();
                tableView.getItems().remove(getTableRow().getIndex());
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {

        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(cellButton);
        }else
            setGraphic(null);
    }
}


