package nju.lighting.vo.doc.giftdoc;


import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

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


    public GiftDocVO(Date time, String creatorId, String docId, DocType type, ArrayList<GiftItemVO> giftItemVOs, String ID, String customer) {
        super(time, creatorId, docId, type);
        this.giftItemVOs = giftItemVOs;
        this.ID = ID;
        this.customer = customer;
    }

    public ArrayList<GiftItemVO> getGifts(){return giftItemVOs;}

    public String getID(){return ID;}

    public String getCustomer(){return customer;}

    public void setGifts(ArrayList<GiftItemVO> giftItemVOs){
        this.giftItemVOs=giftItemVOs;
    }

    public void setID(String ID){
        this.ID=ID;
    }

    public void setCustomer(String customer){
        this.customer=customer;
    }
}
