package nju.lighting.presentation.commodityui;

import javafx.scene.control.TreeCell;
import nju.lighting.vo.commodity.Nameable;

/**
 * Created on 2017/12/17.
 * Description
 *
 * @author 陈俊宇
 */
public class MyTreeCellReadOnly extends TreeCell<Nameable> {


    @Override
    protected void updateItem(Nameable item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            setText(item.getName());
        }
    }
}
