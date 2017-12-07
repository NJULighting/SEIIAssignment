package nju.lighting.vo.doc.giftdoc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/12/4.
 * Description
 *
 * @author 陈俊宇
 */
public class GiftItemListVO {
    private List<GiftItemVO> giftItemVOs;
    private double total=0;

    public GiftItemListVO(List<GiftItemVO> giftItemVOs) {
        this.giftItemVOs = giftItemVOs;

        for (int i=0;i<giftItemVOs.size();i++){
            total+=giftItemVOs.get(i).getSubtotal();
        }
    }

    public List<GiftItemVO> getGiftItemVOs() {
        return giftItemVOs;
    }

    public double getTotal() {
        return total;
    }
}
