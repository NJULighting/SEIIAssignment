package nju.lighting.presentation.documentui;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.mainui.CommonFather;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created on 2017/12/2.
 * Description
 *
 * @author 陈俊宇
 */
public class Doc{
    public static DocVO doc;
    static HashMap<DocType,String> map=new HashMap<>();
    static {
        map.put(DocType.GIFT, "giftdoc.fxml");
        map.put(DocType.ACCOUNT_IN,"AccountIODoc.fxml");
    }



    public static FXMLLoader getLoader() throws IOException {
        return  new FXMLLoader(Doc.class.getResource(map.get(doc.getType())));
    }
}
