package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.DocItem;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.vo.doc.alertdoc.AlertDocItemVO;
import shared.ResultMessage;

public class AlertDocItem implements DocItem {

    private int id;
    private int count;
    private String commodityId;

    AlertDocItem(AlertDocItemPO po) {
        id = po.getId();
        count = po.getCount();
        commodityId = po.getCommodityId();
    }

    AlertDocItem(AlertDocItemVO vo) {
        id = vo.getId();
        count = vo.getCount();
        commodityId = vo.getCommodity().getId();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    AlertDocItemPO toPO(String docId) {
        return new AlertDocItemPO(id, docId, commodityId, count);
    }

    AlertDocItemVO toVO() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return new AlertDocItemVO(commodityInfo.getBasicCommodityItemVO(commodityId), id, count);
    }

    boolean triggered(String commodityId, int count) {
        return this.commodityId.equals(commodityId) && this.count >= count;
    }

    @Override
    public void redFlush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ResultMessage approve() {
        throw new UnsupportedOperationException();
    }
}
