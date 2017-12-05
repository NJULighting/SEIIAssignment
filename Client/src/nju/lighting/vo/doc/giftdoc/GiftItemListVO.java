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
    private double total=0;

    public GiftItemListVO(ArrayList<GiftItemVO> giftItemVOs) {
        this.giftItemVOs = giftItemVOs;

        for (int i=0;i<giftItemVOs.size();i++){
            total+=giftItemVOs.get(i).getSubtotal();
        }
    }

    public ArrayList<GiftItemVO> getGiftItemVOs() {
        return giftItemVOs;
    }

    public double getTotal() {
        return total;
    }
}
