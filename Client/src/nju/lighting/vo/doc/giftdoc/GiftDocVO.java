package nju.lighting.vo.doc.giftdoc;


import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class GiftDocVO extends DocVO {
    private List<GiftItemVO> gifts;
    private String customer;
    private double total = 0;


    public GiftDocVO(Date time, String creatorId, String docId, DocType type,
                     List<GiftItemVO> gifts, String customer) {
        super(time, creatorId, docId, type);
        this.customer = customer;
        this.gifts = gifts;
        for (int i = 0; i < gifts.size(); i++) {
            total += gifts.get(i).getSubtotal();
        }
    }


    public List<GiftItemVO> getGifts() {
        return gifts;
    }

    public double getTotal() {
        return total;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public DocPO toPO() {
        return null;
    }
}
