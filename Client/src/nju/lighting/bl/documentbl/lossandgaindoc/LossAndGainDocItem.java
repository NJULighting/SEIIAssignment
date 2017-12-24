package nju.lighting.bl.documentbl.lossandgaindoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainItemPO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocItemVO;
import shared.LossAndGainItemType;

class LossAndGainDocItem {

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

    String getCommodityName() {
        CommodityInfo info = new CommodityInfoImpl();
        return info.getCommodityNameByID(commodityId);
    }

    LossAndGainItemPO toPO(String docId) {
        return new LossAndGainItemPO(id, docId, commodityId, count, type);
    }

    LossAndGainDocItemVO toVO() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new LossAndGainDocItemVO(commodityInfo.getBasicCommodityItemVO(commodityId), count, type, id);
    }
}
