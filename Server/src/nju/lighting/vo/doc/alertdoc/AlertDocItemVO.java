package nju.lighting.vo.doc.alertdoc;

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

    public void setCommodity(BasicCommodityItemVO commodity) {
        this.commodity = commodity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
