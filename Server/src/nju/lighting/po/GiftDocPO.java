package nju.lighting.po;

import nju.lighting.po.doc.DocPO;

import java.util.ArrayList;

/**
 * Created on 2017/10/19.
 * Description
 * @author 陈俊宇
 */
public class GiftDocPO extends DocPO {
    private ArrayList<GiftItemPO> giftItemPOs;

    private String ID;

    private String customer;

    private String repository;

    public GiftDocPO(ArrayList<GiftItemPO> giftItemPOs, String ID, String customer, String repository) {
        this.giftItemPOs = giftItemPOs;
        this.ID = ID;
        this.customer = customer;
        this.repository = repository;
        this.setTime(100000000);
    }


    public ArrayList<GiftItemPO> getGifts() {
        return giftItemPOs;
    }

    public void setGifts(ArrayList<GiftItemPO> giftItemPOs) {
        this.giftItemPOs = giftItemPOs;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRepository() {
        return ID;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
