package nju.lighting.presentation.commodityui;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeTableCell;
import nju.lighting.presentation.utils.TableViewHelper;

/**
 * Created on 2017/12/25.
 * Description
 * @author 陈俊宇
 */
public class CommodityDetailTreeCell extends TreeTableCell {
    SimpleStringProperty keyWord;

    public CommodityDetailTreeCell(SimpleStringProperty keyWord) {
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
