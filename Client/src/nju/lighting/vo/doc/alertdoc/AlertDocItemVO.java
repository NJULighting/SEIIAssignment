package nju.lighting.vo.doc.alertdoc;

import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;

public class AlertDocItemVO {

    private BasicCommodityItemVO commodity;
    private int id;
    private int count;

    public AlertDocItemVO(BasicCommodityItemVO commodity, int count) {
        this.commodity = commodity;
        this.count = count;
    }

    public AlertDocItemVO(BasicCommodityItemVO commodity, int id, int count) {
        this.commodity = commodity;
        this.id = id;
        this.count = count;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    /**
     * PO for committing
     * @return corresponding po
     */
    AlertDocItemPO toPO() {
        return new AlertDocItemPO(commodity.getId(), count);
    }
}
