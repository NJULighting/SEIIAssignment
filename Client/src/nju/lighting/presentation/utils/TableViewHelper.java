package nju.lighting.presentation.utils;

import com.jfoenix.controls.JFXSlider;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.EditingCell;

/**
 * Created on 2017/12/10.
 * Description
 *
 * @author 陈俊宇
 */
public class TableViewHelper {

    public static void setHeight(TableView tableView) {
        //设置表格的高度和与数据的多少一致，否则数据多的时候表中就会出现滚动条
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(0.83)));

    }

    public static void setHeight(TableView tableView, double multiple) {
        //设置表格的高度和与数据的多少一致，否则数据多的时候表中就会出现滚动条
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(multiple)));

    }

    public static void commonSet(TableView tableView) {
        tableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tableView.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) tableView.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
    }

    public static HBox getHighlightBox(String itemStr, String key) {
        int beginIndex = 0;
        for (int i = 0; i <= itemStr.length() - key.length(); i++) {
            if (itemStr.substring(i, i + key.length()).equals(key)) {
                beginIndex = i;
                break;
            }

        }
        Label first = new Label(itemStr.substring(0, beginIndex));
        Label second = new Label(itemStr.substring(beginIndex, beginIndex + key.length()));
        Label third = new Label(itemStr.substring(beginIndex + key.length()));
        second.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        return new HBox(first, second, third);
    }

    public static void setSliderMarch(JFXSlider slider,TableView tableView){

        //滑块控制表格的左右滑动
        slider.valueProperty().addListener((ObservableValue<? extends Number> ov,
                                            Number old_value,Number new_value) -> {
            tableView.setLayoutX(-new_value.doubleValue());
        });
    }
}
