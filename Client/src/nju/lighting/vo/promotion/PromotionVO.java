package nju.lighting.vo.promotion;


import nju.lighting.vo.UserVO;
import shared.PromotionType;

import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 *
 * @author 陈俊宇
 */
public class PromotionVO {
    private String name; //促销策略名称

    private UserVO creator;

    private PromotionType type;  //促销策略类型

    private Date startDate;  //起始日期

    private Date endDate;  //截止日期

    public PromotionVO(String name, UserVO creator, PromotionType type, Date startDate, Date endDate) {
        this.name = name;
        this.creator = creator;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserVO getCreator() {
        return creator;
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
