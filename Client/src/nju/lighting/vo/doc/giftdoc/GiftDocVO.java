package nju.lighting.vo.doc.giftdoc;


import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class GiftDocVO extends DocVO {
    private GiftItemListVO giftItemListVO;
    private String customer;


    public GiftDocVO(Date time, String creatorId, String docId, DocType type, ArrayList<GiftItemVO> giftItemVOs, String customer) {
        super(time, creatorId, docId, type);
        this.customer = customer;
        double total=0;
        for (int i=0;i<giftItemVOs.size();i++){
            total+=giftItemVOs.get(i).getSubtotal();
        }

        this.giftItemListVO=new GiftItemListVO(giftItemVOs,total);
    }



//    public ArrayList<GiftItemVO> getGifts() {
//        return giftItemVOs;
//    }
//
//    public void setGifts(ArrayList<GiftItemVO> giftItemVOs) {
//        this.giftItemVOs = giftItemVOs;
//    }
//
//    public double getTotal() {
//        return total;
//    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public GiftItemListVO getGiftItemListVO() {
        return giftItemListVO;
    }
}
