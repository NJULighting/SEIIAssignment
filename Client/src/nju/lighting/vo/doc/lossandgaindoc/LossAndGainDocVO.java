package nju.lighting.vo.doc.lossandgaindoc;

import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

public class LossAndGainDocVO extends DocVO {

    private ArrayList<BasicCommodityItemVO> commodities;

    private ArrayList<LossAndGainDocItemVO> items;

    public LossAndGainDocVO(Date time, String creatorId,
                            String docId, DocType type,
                            ArrayList<BasicCommodityItemVO> commodities,
                            ArrayList<LossAndGainDocItemVO> items) {
        super(time, creatorId, docId, type);
        this.commodities = commodities;
        this.items = items;
    }

    public ArrayList<BasicCommodityItemVO> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<BasicCommodityItemVO> commodities) {
        this.commodities = commodities;
    }

    public ArrayList<LossAndGainDocItemVO> getItems() {
        return items;
    }

    public void setItems(ArrayList<LossAndGainDocItemVO> items) {
        this.items = items;
    }

    @Override
    public DocPO toPO() {
        return null;
    }
}
