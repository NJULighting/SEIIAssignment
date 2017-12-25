package nju.lighting.bl.promotionbl;


import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.promotiondataservice.PromotionDataService;
import nju.lighting.po.promotion.PromotionPO;
import nju.lighting.po.promotion.PromotionPackageItemPO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.promotion.PromotionVO;
import shared.CustomerGrade;
import shared.PromotionBuildInfo;
import shared.PromotionType;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created on 2017/11/7.
 * Description
 * @author 陈俊宇
 */
class Promotion {
    private int id;
    private String name;
    private PromotionType type;
    private String userID;
    private Date startDate;
    private Date endDate;
    private Date createTime;
    private CustomerGrade level;
    private double priceTarget;
    private PromotionItemList itemList;
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
        itemList = new PromotionItemList(info.getGoods());
        off = info.getOff();
        vouchers = info.getVouchers();
        vouchersEndDate = info.getVouchersEndDate();

        createTime = new Date();
        id = 0;

        // Get user's id
        UserInfo userInfo = new UserInfoImpl();
        userID = userInfo.getIDOfSignedUser();
    }

    Promotion(PromotionPO po) {
        id = po.getId();
        name = po.getName();
        type = po.getType();
        startDate = po.getStartDate();
        endDate = po.getEndDate();
        level = po.getLevel();
        priceTarget = po.getTotal();
        off = po.getOff();
        vouchersEndDate = po.getVouchersEndDate();
        vouchers = po.getVouchers();
        createTime = po.getTime();
        userID = po.getCreatorID();

        itemList = new PromotionItemList();
        itemList.addAll(po.getGoods());
    }


    PromotionPO buildPOForCreation() {
        // Transfer goods
        List<PromotionPackageItemPO> itemPOList = itemList.toPOList(id);

        return new PromotionPO(name, userID, type, startDate, endDate, createTime, level
                , priceTarget, itemPOList, off, vouchers, vouchersEndDate);
    }

    PromotionVO toVO() {
        UserInfo info = new UserInfoImpl();
        UserVO creatorVO = info.getUserVOByID(userID);
        if (creatorVO == null)
            throw new NoSuchElementException("Invalid user id");
        return new PromotionVO(id, name, creatorVO, type, startDate, endDate, level, priceTarget, itemList.getGoods(), off, vouchers, vouchersEndDate);
    }

    ResultMessage changeInfo(PromotionVO vo) {
        name = vo.getName();
        type = vo.getType();
        startDate = vo.getStartDate();
        endDate = vo.getEndDate();
        level = vo.getLevel();
        priceTarget = vo.getPrice();
        itemList = new PromotionItemList(vo.getGoods());
        off = vo.getOff();
        vouchers = vo.getVouchers();
        vouchersEndDate = vo.getVouchersEndDate();
        return updateToDatabase();
    }

    private ResultMessage updateToDatabase() {
        try {
            PromotionDataService dataService = DataFactory.getDataBase(PromotionDataService.class);
            return dataService.update(new PromotionPO(id, userID, name, type, startDate, endDate, createTime, level
                    , priceTarget, itemList.toPOList(id), off, vouchers, vouchersEndDate));
        } catch (RemoteException | NamingException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    public void setId(int id) {
        this.id = id;
    }
}
