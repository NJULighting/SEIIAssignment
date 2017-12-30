package nju.lighting.presentation.customerui;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.utils.TableViewHelper;

/**
 * Created on 2017/12/30.
 * Description
 *
 * @author 陈俊宇
 */
public class HighLightCell extends TableCell {
    StringProperty keyWord;
    public HighLightCell(StringProperty keyWord){
        this.keyWord=keyWord;
    }
    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

                String key=keyWord.getValue();
                String itemStr=item.toString();
                if (key!=null&& itemStr.contains(key)){
                    HBox hBox=TableViewHelper.getHighlightBox(itemStr,key);
                    hBox.setPadding(new Insets(12,0,0,0));
                    setGraphic(hBox);
                    setText(null);
                }else {
                    setGraphic(null);
                    setText(itemStr);
                }






        }
    }
}
