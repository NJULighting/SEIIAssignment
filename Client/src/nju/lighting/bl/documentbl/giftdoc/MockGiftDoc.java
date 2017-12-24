package nju.lighting.bl.documentbl.giftdoc;

import nju.lighting.bl.customerbl.Customer;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/14.
 * Description
 * @author 陈俊宇
 */
@Deprecated
public class MockGiftDoc extends GiftDoc {
    public MockGiftDoc(String docId, DocType docType, String userId, Date time, ArrayList<GiftDocItem> items, Customer customer) {
        super(docId, docType, userId, time, items, customer);
    }
}
