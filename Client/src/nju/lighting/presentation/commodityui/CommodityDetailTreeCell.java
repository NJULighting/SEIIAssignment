package nju.lighting.presentation.commodityui;

import javafx.scene.control.TreeTableCell;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.Nameable;

/**
 * Created on 2017/12/25.
 * Description
 *
 * @author 陈俊宇
 */
public class CommodityDetailTreeCell extends TreeTableCell {
    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            CommodityCategoryItem value = (CommodityCategoryItem) getTreeTableView().getTreeItem(getIndex()).getValue();
            if (value.getId().equals("")) {
                setText("" );
            } else {
                setText(item.toString());
            }
        }
    }
}
