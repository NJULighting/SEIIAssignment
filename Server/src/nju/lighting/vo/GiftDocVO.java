package nju.lighting.vo;



import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftDocVO extends DocVO {
    private ArrayList<CommodityVO> gifts;

    private String ID;

    private CustomerVO customer;

    private String repository;

    public ArrayList<CommodityVO> getGifts(){return gifts;}

    public String getID(){return ID;}

    public String getRepository(){return ID;}

    public CustomerVO getCustomer(){return customer;}

    public void setGifts(ArrayList<CommodityVO> gifts){
        this.gifts=gifts;
    }

    public void setID(String ID){
        this.ID=ID;
    }

    public void setRepository(String repository){
        this.repository=repository;
    }

    public void setCustomer(CustomerVO customer){
        this.customer=customer;
    }
}
