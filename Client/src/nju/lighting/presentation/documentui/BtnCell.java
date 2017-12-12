package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.control.Skinnable;
import javafx.scene.control.TableCell;
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
                getTableView().getItems().remove(getTableRow().getIndex());
                System.out.println(getTableView().getItems().size());
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        System.out.println(empty);
        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(cellButton);
        }else
            setGraphic(null);
    }
}


