package nju.lighting.vo.giftdoc;


import nju.lighting.vo.DocVO;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftDocVO extends DocVO {
    private ArrayList<GiftItemVO> giftItemVOs;

    private String ID;

    private String customer;

    private String repository;

    public GiftDocVO(ArrayList<GiftItemVO> giftItemVOs, String ID, String customer, String reVOsitory) {
        this.giftItemVOs = giftItemVOs;
        this.ID = ID;
        this.customer = customer;
        this.repository = reVOsitory;
        this.setTime(100000000);
    }


    public ArrayList<GiftItemVO> getGifts(){return giftItemVOs;}

    public String getID(){return ID;}

    public String getRepository(){return ID;}

    public String getCustomer(){return customer;}

    public void setGifts(ArrayList<GiftItemVO> giftItemVOs){
        this.giftItemVOs=giftItemVOs;
    }

    public void setID(String ID){
        this.ID=ID;
    }

    public void setRepository(String repository){
        this.repository=repository;
    }

    public void setCustomer(String customer){
        this.customer=customer;
    }
}
