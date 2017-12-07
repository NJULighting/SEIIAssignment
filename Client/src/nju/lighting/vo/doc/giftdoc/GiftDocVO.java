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

    public GiftDocVO(Date time, String creatorId, String docId, DocType type,
                     ArrayList<GiftItemVO> gifts, String customer) {
        super(time, creatorId, docId, type);
        this.giftItemListVO = new GiftItemListVO(gifts);
        this.customer = customer;
    }

    public GiftItemListVO getGiftItemListVO() {
        return giftItemListVO;
    }



    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
