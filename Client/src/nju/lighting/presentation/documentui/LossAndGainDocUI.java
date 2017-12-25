package nju.lighting.presentation.documentui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.commodityui.CommodityPicker;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainItem;
import nju.lighting.presentation.documentui.lossandgaindoc.LossAndGainList;
import nju.lighting.presentation.mainui.CommodityUpper;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.LossAndGainItemType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/25.
 * Description
 *
 * @author 陈俊宇
 */
public class LossAndGainDocUI implements Initializable,CommodityUpper {

    LossAndGainDocMain main;
    ObservableList<BasicCommodityItemVO> commodities= FXCollections.observableArrayList();
    ObservableList<LossAndGainItem> docItemList;

    FXMLLoader loader;

    @FXML
    HBox tableContainer;

    @Override
    public void back() {
        main.back();
    }

    @Override
    public void addCommodity(List<BasicCommodityItemVO> commodity) {
        commodities.addAll(commodity);
    }


    public void setMain(LossAndGainDocMain main) {
        this.main = main;
    }

    @FXML
    void removeAll(){
        docItemList.clear();
    }

    @FXML
    void chooseCommodity() throws IOException {
        loader=new FXMLLoader(getClass().getResource("../commodityui/CommodityPicker.fxml"));
        main.setChildren(loader.load(),">选择商品");
        CommodityPicker controller=loader.getController();
        controller.setUpper(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader=new FXMLLoader(getClass().getResource("lossandgaindoc/LossAndGainList.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LossAndGainList controller=loader.getController();
        controller.setEditable();
        docItemList=controller.getData();

        commodities.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()){
                    if (c.wasAdded()){
                        docItemList.addAll(
                                c.getAddedSubList().stream()
                                        .map(x-> new LossAndGainItem(x,1, LossAndGainItemType.LOSS))
                                        .collect(Collectors.toList())
                        );
                    }
                }
            }
        });


    }


    public ObservableList<BasicCommodityItemVO> getCommodities() {
        return commodities;
    }


}
