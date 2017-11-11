package nju.lighting.vo.doc.alertdoc;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

public class AlertDocVO extends DocVO {

    private ArrayList<BasicCommodityItemVO> commodities;

    private ArrayList<AlertDocItemVO> items;

    public AlertDocVO(Date time, String creatorId, String docId,
                      DocType type, ArrayList<BasicCommodityItemVO> commodities,
                      ArrayList<AlertDocItemVO> items) {
        super(time, creatorId, docId, type);
        this.commodities = commodities;
        this.items = items;
    }

}
