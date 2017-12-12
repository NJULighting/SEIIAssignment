package nju.lighting.presentation.utils;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    public static void disableReorder(TableView tableView){
        tableView.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) tableView.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
    }

    public static void Edit( TableColumn<CommodityItem ,Integer> count){
        Callback<TableColumn<CommodityItem, Integer>,
                TableCell<CommodityItem, Integer>> cellFactory
                = (TableColumn<CommodityItem, Integer> p) -> new EditingCell();

        count.setCellFactory(cellFactory);

        count.setOnEditCommit(
                (TableColumn.CellEditEvent<CommodityItem, Integer> t) -> {
                    CommodityItem selected = t.getTableView().getItems().get(
                            t.getTablePosition().getRow());
                    selected.setCount(t.getNewValue());
                    selected.setSubtotal(selected.getPrice() * selected.getCount());

                });
    }
}
