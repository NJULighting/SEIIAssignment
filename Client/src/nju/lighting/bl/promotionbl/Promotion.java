package nju.lighting.bl.promotionbl;


import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.po.promotion.PromotionPO;
import nju.lighting.po.promotion.PromotionPackageItemPO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerGrade;
import shared.PromotionBuildInfo;
import shared.PromotionType;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created on 2017/11/7.
 * Description
 * @author 陈俊宇
 */
class Promotion  {
    private int id;
    private String name;
    private PromotionType type;
    private String userID;
    private Date startDate;
    private Date endDate;
    private Date createTime;
    private CustomerGrade level;
    private double priceTarget;
    private List<GiftItemVO> goods;
    private double off;
    private double vouchers;
    private Date vouchersEndDate;

    Promotion(PromotionBuildInfo info) {
        name = info.getName();
        type = info.getType();
        startDate = info.getStartDate();
        endDate = info.getEndDate();
        level = info.getLevel();
        priceTarget = info.getPrice();
        goods = info.getGoods();
        off = info.getOff();
        vouchers = info.getVouchers();
        vouchersEndDate = info.getVouchersEndDate();

        createTime = new Date();
        id = 0;

        // Get user's id
        UserInfo userInfo = new UserInfoImpl();
        userID = userInfo.getIDOfSignedUser();
    }

    PromotionPO buildPO() {
        // Transfer goods
        List<PromotionPackageItemPO> itemPOList = goods.stream()
                .map(giftItemVO -> new PromotionPackageItemPO(giftItemVO.getCommodityID()))
                .collect(Collectors.toList());

        // TODO: 2017/12/7 Add user id here
        return new PromotionPO(name, userID, type, startDate, endDate, createTime, level
                , priceTarget, itemPOList, off, vouchers, vouchersEndDate);
    }

    PromotionVO toVO(){
        UserInfo info = new UserInfoImpl();
        UserVO creatorVO = info.getUserVOByID(userID);
        if (creatorVO == null)
            throw new NoSuchElementException("Invalid user id");
        return new PromotionVO(id, name, creatorVO, type, startDate, endDate, level, priceTarget, goods, off, vouchers, vouchersEndDate);
    }

    public void setId(int id) {
        this.id = id;
    }
}
