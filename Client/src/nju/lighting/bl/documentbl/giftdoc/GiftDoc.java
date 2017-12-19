package nju.lighting.bl.documentbl.giftdoc;

import nju.lighting.bl.customerbl.Customer;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/14.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftDoc extends Doc {

    ArrayList<GiftDocItem> items;

    private Customer customer;

    Date time;


    public GiftDoc(String  docId, DocType docType, String userId, Date time,ArrayList<GiftDocItem> items,Customer customer) {
        super(docId, docType, userId, time);
        this.items=items;
        this.docType=DocType.GIFT;
        this.customer=customer;
    }

    public ArrayList<GiftDocItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GiftDocItem> items) {
        this.items = items;
    }

    @Override
    public void approve() {

    }

    @Override
    public ResultMessage reject() {
        return null;
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        return null;
    }

    @Override
    public DocPO toPO() {
        return null;
    }

    @Override
    public boolean containsCustomer(String customerId) {
        return false;
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return false;
    }

    @Override
    public boolean containsRepository(String repository) {
        return false;
    }

}
