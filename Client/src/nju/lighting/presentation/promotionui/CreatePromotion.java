package nju.lighting.presentation.promotionui;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.PromotionType;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/28.
 * Description
 *
 * @author 陈俊宇
 */
public class CreatePromotion implements Initializable {
    Upper upper;


    ObservableList<BasicCommodityItemVO> commodities = FXCollections.observableArrayList();
    ObservableList<CommodityItem> itemList;

    HashMap<PromotionType,String > typeToUrl=new HashMap<>();
    @FXML
    VBox verticalBox;

    FXMLLoader typeLoader;

    @FXML
    Button chooseCommodityBtn;

    @FXML
    Pane tableContainer;

    public void setType(PromotionType type) throws IOException {
        typeLoader =new FXMLLoader(getClass().getResource(typeToUrl.get(type)));
        verticalBox.getChildren().add(typeLoader.load());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeToUrl.put(PromotionType.Combo,"CreateCombo.fxml");
        typeToUrl.put(PromotionType.CustomerOriented,"CreateCustomerOrientedUI.fxml");
        typeToUrl.put(PromotionType.PriceOriented,"CreatePriceOrientedUI.fxml");
        chooseCommodityBtn.setOnAction(e -> CommodityHelper.chooseCommodity(upper, commodities));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../documentui/CommodityList.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommodityList controller = loader.getController();
        controller.setGiftAndEditable();
        itemList = controller.getGiftObservableList();

        commodities.addListener(new ListChangeListener<BasicCommodityItemVO>() {
            @Override
            public void onChanged(Change<? extends BasicCommodityItemVO> c) {
                while (c.next()){
                    itemList.addAll(c.getAddedSubList().stream()
                            .map(x-> new CommodityItem(x,1))
                            .collect(Collectors.toList()));
                }
            }
        });
    }

    public void setUpper(Upper upper) {
        this.upper = upper;
    }
}
