package nju.lighting.vo;

import nju.lighting.po.CommodityPO;
import nju.lighting.po.CustomerPO;
import nju.lighting.po.DocPO;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftDocVO extends DocPO {
    private ArrayList<CommodityPO> gifts;

    private String ID;

    private CustomerPO customer;

    private String repository;

    public ArrayList<CommodityPO> getGifts(){return gifts;}

    public String getID(){return ID;}

    public String getRepository(){return ID;}

    public CustomerPO getCustomer(){return customer;}

    public void setGifts(ArrayList<CommodityPO> gifts){
        this.gifts=gifts;
    }

    public void setID(String ID){
        this.ID=ID;
    }

    public void setRepository(String repository){
        this.repository=repository;
    }

    public void setCustomer(CustomerPO customer){
        this.customer=customer;
    }
}
