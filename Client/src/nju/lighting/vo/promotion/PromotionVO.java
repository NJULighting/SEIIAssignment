package nju.lighting.vo.promotion;


import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import shared.CustomerGrade;
import shared.PromotionType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 *
 * @author 陈俊宇
 */
public class PromotionVO {
    private String name; //促销策略名称

    private PromotionType type;  //促销策略类型

    private Date startDate;  //起始日期

    private Date endDate;  //截止日期

    public PromotionVO(String name, PromotionType type, Date startDate, Date endDate) {
        this.name=name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public PromotionType getType() {
        return type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
