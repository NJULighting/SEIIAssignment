package nju.lighting.vo.doc.giftdoc;

import java.util.ArrayList;

/**
 * Created on 2017/12/4.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftItemListVO {
    private ArrayList<GiftItemVO> giftItemVOs;
    private double total;

    public GiftItemListVO(ArrayList<GiftItemVO> giftItemVOs, double total) {
        this.giftItemVOs = giftItemVOs;
        this.total = total;
    }

    public ArrayList<GiftItemVO> getGiftItemVOs() {
        return giftItemVOs;
    }

    public double getTotal() {
        return total;
    }
}
