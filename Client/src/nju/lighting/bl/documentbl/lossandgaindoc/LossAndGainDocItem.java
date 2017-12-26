package nju.lighting.bl.documentbl.lossandgaindoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.bl.repositorybl.RepositoryInfo;
import nju.lighting.bl.repositorybl.RepositoryInfoImpl;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainItemPO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocItemVO;
import shared.LossAndGainItemType;
import shared.RepositoryChangeType;
import shared.ResultMessage;

class LossAndGainDocItem implements DocItem {

    private int count;
    private int id;
    private LossAndGainItemType type;
    private String commodityId;

    LossAndGainDocItem(LossAndGainDocItemVO vo) {
        id = vo.getId();
        count = vo.getCount();
        type = vo.getType();
        commodityId = vo.getCommodity().getId();
    }

    LossAndGainDocItem(LossAndGainItemPO po) {
        id = po.getId();
        count = po.getCount();
        type = po.getType();
        commodityId = po.getCommodityId();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LossAndGainItemType getType() {
        return type;
    }

    public void setType(LossAndGainItemType type) {
        this.type = type;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public int getId() {
        return id;
    }

    /**
     * Get total value of the commodity in this item. This method uses
     * recent sell price for calculating the value. If the value is positive,
     * its type is <tt>GAIN</tt>. If the value if negative, its type is <tt>LOSS</tt>
     */
    public double getValue() {
        double value = getAmount();
        if (type == LossAndGainItemType.LOSS)
            value = -value;

        return value;
    }

    String getCommodityName() {
        CommodityInfo info = new CommodityInfoImpl();
        return info.getCommodityNameByID(commodityId);
    }

    private double getAmount() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return commodityInfo.getCommodityRecentSellPrice(commodityId) * count;
    }

    LossAndGainItemPO toPO(String docId) {
        return new LossAndGainItemPO(id, docId, commodityId, count, type);
    }

    LossAndGainDocItemVO toVO() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new LossAndGainDocItemVO(commodityInfo.getBasicCommodityItemVO(commodityId), count, type, id);
    }

    @Override
    public void redFlush() {
        id = 0;
        count = -count;
    }

    @Override
    public ResultMessage approve() {
        RepositoryInfo repositoryInfo = new RepositoryInfoImpl();
        RepositoryChangeType repositoryChangeType = type == LossAndGainItemType.LOSS ?
                RepositoryChangeType.LOSS : RepositoryChangeType.GAIN;
        return repositoryInfo.changeRepository(commodityId, count, getAmount(), repositoryChangeType);
    }
}
