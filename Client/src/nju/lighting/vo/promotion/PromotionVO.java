package nju.lighting.vo.promotion;


import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import shared.CustomerGrade;
import shared.PromotionType;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description:
 * @author 陈俊宇
 */
public class PromotionVO {
    private final int id;
    private final UserVO creator;
    private String name; //促销策略名称
    private PromotionType type;  //促销策略类型
    private Date startDate;  //起始日期
    private Date endDate;  //截止日期
    private CustomerGrade level; //针对不同级别用户中用户的级别
    private List<GiftItemVO> goods; //组合特价包裹
    private double price;  //针对不同总价的促销策略中的总价
    private double off;  //折让金额
    private double vouchers; //代金券金额
    private Date vouchersEndDate;  //代金券截止日期

    public PromotionVO(int id, String name, UserVO creator, PromotionType type, Date startDate, Date endDate
            , CustomerGrade level, double price, List<GiftItemVO> goods, double off, double vouchers, Date vouchersEndDate) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.price = price;
        this.goods = goods;
        this.off = off;
        this.vouchers = vouchers;
        this.vouchersEndDate = vouchersEndDate;
    }

    public UserVO getCreator() {
        return creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PromotionType getType() {
        return type;
    }

    public void setType(PromotionType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public CustomerGrade getLevel() {
        return level;
    }

    public void setLevel(CustomerGrade level) {
        this.level = level;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<GiftItemVO> getGoods() {
        return goods;
    }

    public void setGoods(List<GiftItemVO> goods) {
        this.goods = goods;
    }

    public double getOff() {
        return off;
    }

    public void setOff(double off) {
        this.off = off;
    }

    public double getVouchers() {
        return vouchers;
    }

    public void setVouchers(double vouchers) {
        this.vouchers = vouchers;
    }

    public Date getVouchersEndDate() {
        return vouchersEndDate;
    }

    public void setVouchersEndDate(Date vouchersEndDate) {
        this.vouchersEndDate = vouchersEndDate;
    }

    @Override
    public String toString() {
        return "PromotionVO{" +
                "id=" + id +
                ", creator=" + creator +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", level=" + level +
                ", goods=" + goods +
                ", price=" + price +
                ", off=" + off +
                ", vouchers=" + vouchers +
                ", vouchersEndDate=" + vouchersEndDate +
                '}';
    }
}
