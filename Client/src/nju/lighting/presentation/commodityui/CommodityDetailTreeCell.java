package nju.lighting.presentation.commodityui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeTableCell;
import nju.lighting.presentation.utils.TableViewHelper;

/**
 * Created on 2017/12/25.
 * Description 商品管理界面 TreeTableView 右边（不是树节点）的普通cell 的 cell实现
 * @author 陈俊宇
 */
public class CommodityDetailTreeCell extends TreeTableCell {
    private SimpleStringProperty keyWord;

    CommodityDetailTreeCell(SimpleStringProperty keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            CommodityCategoryItem value = (CommodityCategoryItem) getTreeTableView().getTreeItem(getIndex()).getValue();
            if (value.getId().equals("")) {
                setText("");
                setGraphic(null);
            } else {
                String key = keyWord.getValue();
                String itemStr = item.toString();
                if (key != null && itemStr.contains(key)) {
                    setGraphic(TableViewHelper.getHighlightBox(itemStr, key));
                    setText(null);
                } else {
                    setGraphic(null);
                    setText(itemStr);
                }


            }
        }
    }
}
