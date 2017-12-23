package nju.lighting.presentation.utils;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import javafx.beans.binding.Bindings;
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

    public static void commonSet(TableView tableView){
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        //设置表格的高度和与数据的多少一致，否则数据多的时候表中就会出现滚动条
        if(tableView.fixedCellSizeProperty().getValue()!=-1)
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(0.86)));

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
                    int index= t.getTablePosition().getRow();

                    CommodityItem selected = t.getTableView().getItems().get(
                            index);
                    selected.setCount(t.getNewValue());
                    selected.setSubtotal(selected.getPrice() * selected.getCount());


                });
    }
}
