package nju.lighting.vo.doc.alertdoc;

import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

public class AlertDocItemVO {

    private BasicCommodityItemVO commodity;

    private int count;

    public AlertDocItemVO(BasicCommodityItemVO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }

    public int getCount() {
        return count;
    }

    /**
     * PO for committing
     * @return corresponding po
     */
    AlertDocItemPO toPO() {
        return new AlertDocItemPO(commodity.getId(), count);
    }
}
