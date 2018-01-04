package nju.lighting.presentation.factory;

import nju.lighting.bl.documentbl.DocController;
import nju.lighting.blservice.documentblservice.DocBLService;

/**
 * Created on 2017/12/30.
 * Description
 * @author 陈俊宇
 */
public class DocBLServiceFactory {
    private static DocBLService docBLService = new DocController();

    public static DocBLService getDocBLService() {
        return docBLService;
    }
}
