package nju.lighting.vo.doc.alertdoc;

import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlertDocVO extends DocVO {

    private List<BasicCommodityItemVO> commodities;
    private List<AlertDocItemVO> items;

    public AlertDocVO(Date time, String creatorId, String docId, DocType type) {
        super(time, creatorId, docId, type);
    }


    @Override
    public DocPO toPO() {
        return null;
    }
}
