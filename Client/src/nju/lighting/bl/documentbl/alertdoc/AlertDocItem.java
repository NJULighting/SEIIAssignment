package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.vo.doc.alertdoc.AlertDocItemVO;

public class AlertDocItem {

    private int count;
    private String commodityId;

    AlertDocItem(AlertDocItemPO po) {
        count = po.getCount();
        commodityId = po.getCommodityId();
    }

    AlertDocItem(AlertDocItemVO vo) {
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
        return new AlertDocItemPO(0, docId, commodityId, count);
    }
}
