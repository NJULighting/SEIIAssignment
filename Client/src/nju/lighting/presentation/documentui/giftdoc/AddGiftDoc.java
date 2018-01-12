package nju.lighting.presentation.documentui.giftdoc;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.presentation.documentui.AddDoc;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.Modifiable;
import nju.lighting.presentation.factory.DocBLServiceFactory;
import nju.lighting.presentation.mainui.Client;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.CommodityHelper;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.presentation.utils.TextFieldHelper;
import nju.lighting.vo.CustomerVO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2018/1/6.
 * Description
 *
 * @author 陈俊宇
 */
public class AddGiftDoc extends AddDoc implements Initializable, Upper {


    @FXML
    Button chooseCustomerBtn, chooseCommodityBtn;

    @FXML
    Pane mainPane;

    @FXML
    HBox container, tableContainer;

    @FXML
    Label sub;

    @FXML
    JFXTextField customerText;

    @FXML
    JFXButton commitBtn;


    private Upper upper = this;

    private ObservableList<BasicCommodityItemVO> commodityList = FXCollections.observableArrayList();

    private ObservableList<CommodityItem> itemList;

    private SimpleObjectProperty<CustomerVO> customerProperty = new SimpleObjectProperty<>();

    private DocBLService blService = DocBLServiceFactory.getDocBLService();

    protected GiftDocVO getDoc() {
        if (customerProperty.getValue() != null && itemList.size() != 0) {
            return new GiftDocVO(new Date(),
                    Client.getUserVO().getID(),
                    itemList.stream()
                            .map(CommodityItem::toGiftItem)
                            .collect(Collectors.toList()),
                    customerProperty.getValue().getID());
        } else return null;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("nju/lighting/presentation/documentui/CommodityList.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommodityList listController = loader.getController();
        listController.setGiftAndEditable();

        itemList = listController.getGiftObservableList();

        commodityList.addListener((ListChangeListener<? super BasicCommodityItemVO>) c -> {
            while (c.next()) {
                itemList.addAll(c.getAddedSubList().stream()
                        .map(x -> new CommodityItem(x, 1, true))
                        .collect(Collectors.toList()));
            }
        });

        chooseCommodityBtn.setOnAction(e -> CommodityHelper.chooseCommodity(upper, commodityList));

        customerProperty.addListener(c -> {
            if (customerProperty.getValue() != null)
                customerText.setText(customerProperty.getValue().getName());
        });

        chooseCustomerBtn.setOnAction(e -> CustomerHelper.setCustomer(upper, customerProperty));

        commitBtn.setOnAction(e -> DocHelper.commitDoc(getDoc()));
    }

    @Override
    public void back() {
        setChildren(mainPane, "");
    }

    @Override
    public void setChildren(Node node, String title) {
        container.getChildren().setAll(node);
        sub.setText(title);
    }

    @Override
    public void modify(Upper upper, DocVO docVO, boolean redFlush) {
        this.upper = upper;
        GiftDocVO giftDocVO = (GiftDocVO) docVO;

        itemList.setAll(giftDocVO.getGifts().stream()
                .map(CommodityItem::new)
                .collect(Collectors.toList()));

        customerProperty.set(CustomerHelper.getCustomer(((GiftDocVO) docVO).getCustomerID()));
        TextFieldHelper.addDeleteMenuItem(customerText, customerProperty);

        super.modify(upper, docVO, redFlush);

    }

    @Override
    public Node getMainPane() {
        return mainPane;
    }
}
