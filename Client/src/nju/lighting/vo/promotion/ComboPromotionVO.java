package nju.lighting.vo.promotion;


import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import shared.PromotionType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/19.
 * Description
 *
 * @author 陈俊宇
 */
public class ComboPromotionVO extends PromotionVO{
    private ArrayList<GiftItemVO> combo;   //组合商品列表

    private double total;   //组合商品总价

    private double off;     //组合商品降价额度

    public ComboPromotionVO(String name, PromotionType type, Date startDate, Date endDate, ArrayList<GiftItemVO> combo, double total, double off) {
        super(name, type, startDate, endDate);
        this.combo = combo;
        this.total = total;
        this.off = off;
    }

    public ArrayList<GiftItemVO> getCombo() {
        return combo;
    }

    public double getTotal() {
        return total;
    }

    public double getOff() {
        return off;
    }
}
