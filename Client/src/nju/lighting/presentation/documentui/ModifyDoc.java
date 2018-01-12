package nju.lighting.presentation.documentui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created on 2018/1/6.
 * Description 起到根据单据类型分配 url的作用， 分配到的url 是新建单据的界面 通过调用的他们的modify方法初始化
 *
 * @author 陈俊宇
 */
public class ModifyDoc {

    private static HashMap<DocType,String> typeStringHashMap=new HashMap<>();

    static {
        typeStringHashMap.put(DocType.GIFT, "giftdoc/AddGiftDoc.fxml");
        typeStringHashMap.put(DocType.ACCOUNT_IN, "accountiodoc/AddAccountIODoc.fxml");
        typeStringHashMap.put(DocType.ACCOUNT_OUT, "accountiodoc/AddAccountIODoc.fxml");
        typeStringHashMap.put(DocType.LOSS_AND_GAIN, "lossandgaindoc/AddLossAndGainDoc.fxml");
        typeStringHashMap.put(DocType.SALES, "salesdoc/AddSalesDoc.fxml");
        typeStringHashMap.put(DocType.SALES_RETURN, "salesdoc/AddSalesReturnDoc.fxml");
        typeStringHashMap.put(DocType.STOCK, "stockdoc/AddStockDoc.fxml");
        typeStringHashMap.put(DocType.STOCK_RETURN, "stockdoc/AddStockReturnDoc.fxml");
        typeStringHashMap.put(DocType.ALERT, "alertdoc/AddAlertDoc.fxml");
        typeStringHashMap.put(DocType.COST, "costdoc/AddCostDoc.fxml");
    }


    public static Node getNode(Upper upper, DocVO docVO, boolean redFlush){

        FXMLLoader loader=new FXMLLoader(ModifyDoc.class.getResource(typeStringHashMap.get(docVO.getType())));

        try {
            loader.load();
            Modifiable controller=loader.getController();
            controller.modify(upper,docVO, redFlush);
            return controller.getMainPane();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
