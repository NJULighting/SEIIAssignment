package nju.lightingServer.po.doc.giftdoc;

import nju.lightingServer.po.doc.DocPO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/19.
 * Description
 * @author 陈俊宇
 */
public class GiftDocPO extends DocPO {
    private ArrayList<GiftItemPO> giftItemPOs;

    private String customerID;

    private String repositoryID;

    private double total;


    public GiftDocPO(String id, DocType docType, String userId, Date time, ArrayList<GiftItemPO> giftItemPOs, String customerID, String repositoryID, double total) {
        super(id, docType, userId, time);
        this.giftItemPOs = giftItemPOs;
        this.customerID = customerID;
        this.repositoryID = repositoryID;
        this.total = total;
    }

    public ArrayList<GiftItemPO> getGifts() {
        return giftItemPOs;
    }

    public void setGifts(ArrayList<GiftItemPO> giftItemPOs) {
        this.giftItemPOs = giftItemPOs;
    }

    public String getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(String repository) {
        this.repositoryID = repository;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customer) {
        this.customerID = customer;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
