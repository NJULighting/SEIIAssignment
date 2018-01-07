package nju.lighting.presentation.documentui.salesdoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.documentui.CommodityItem;
import nju.lighting.presentation.documentui.CommodityList;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.utils.CustomerHelper;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created on 2018/1/5.
 * Description
 *
 * @author 陈俊宇
 */
public class SalesDoc implements Initializable {
    @FXML
    ImageView returnImage, saleImage;

    @FXML
    HBox imageBox, tableContainer;

    @FXML
    Label customer, creator, salesman, repository, comments,accountBefore,discount,voucher,account,idLabel;


    private SalesDocVO salesDocVO;

    public SalesDoc() {
        salesDocVO = (SalesDocVO) Doc.getDoc();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        customer.setText(CustomerHelper.getCustomer(salesDocVO.getCustomerId()).getName());
        creator.setText(UserHelper.getUserString(salesDocVO.getCreatorId()));
        salesman.setText(UserHelper.getUserString(salesDocVO.getSalesman()));
        repository.setText(salesDocVO.getRepository());
        comments.setText(salesDocVO.getRemarks());
        account.setText(salesDocVO.getFinalAmount()+"");
        accountBefore.setText(salesDocVO.getBeforeDiscountAmount()+"");
        discount.setText(salesDocVO.getDiscount()+"");
        voucher.setText(salesDocVO.getVoucher()+"");
        idLabel.setText(salesDocVO.getDocId());


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../CommodityList.fxml"));
        try {
            tableContainer.getChildren().add(loader.load());
            ((CommodityList) loader.getController()).getGiftObservableList()
                    .addAll(salesDocVO.getItems().stream()
                            .map(x -> new CommodityItem(x))
                            .collect(Collectors.toList()));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
