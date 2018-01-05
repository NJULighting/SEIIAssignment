package nju.lighting.presentation.documentui.accountiodoc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.documentui.Doc;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import shared.DocType;

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
public class AccountIODoc implements Initializable {
    @FXML
    Label creatorLabel, customerNameLabel, createTimeLabel,totalLabel;

    @FXML
    ImageView inImage,outImage;

    @FXML
    HBox tableContainer,imageContainer;

    AccountIODocVO accountIODocVO;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountIODocVO = (AccountIODocVO) Doc.getDoc();

        if (accountIODocVO.getType()== DocType.ACCOUNT_IN)
            imageContainer.getChildren().remove(outImage);
        else
            imageContainer.getChildren().remove(inImage);

        creatorLabel.setText(accountIODocVO.getCreatorId());
        customerNameLabel.setText(accountIODocVO.getCustomer());
        createTimeLabel.setText(DateHelper.approximateTime(accountIODocVO.getTime()));
        totalLabel.setText(accountIODocVO.getTotal()+"");


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountTransferList.fxml"));
            tableContainer.getChildren().add(loader.load());
            AccountTransferList controller = loader.getController();
            controller.getObservableList().addAll(
                    accountIODocVO.getTransferAccountList().stream()
                            .map(x -> new AccountTransferItem(x))
                            .collect(Collectors.toList())
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
