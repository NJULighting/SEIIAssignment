package nju.lighting.bl.repositorybl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.po.repository.RepositoryChangePO;
import nju.lighting.vo.repository.RepositoryChangeVO;
import shared.RepositoryChangeType;

import java.util.Date;

/**
 * 库存变化信息领域模型对象
 */
class RepositoryChange {

    private int id;
    private String commodityId;
    private RepositoryChangeType type;
    private int count;
    private double amount;
    private Date date;

    RepositoryChange(RepositoryChangePO po) {
        id = po.getId();
        commodityId = po.getCommodityId();
        type = po.getType();
        count = po.getCount();
        amount = po.getAmount();
        date = po.getDate();
    }

    RepositoryChange(String commodityId, RepositoryChangeType type, int count, double amount, Date date) {
        this.commodityId = commodityId;
        this.type = type;
        this.count = count;
        this.amount = amount;
        this.date = date;
    }

    public RepositoryChangeVO toVO() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new RepositoryChangeVO(commodityInfo.getCommodityNameByID(commodityId), commodityId, type, count, amount, date);
    }

    RepositoryChangePO toPOForCreation() {
        return new RepositoryChangePO(commodityId, type, count, amount, date);
    }
}