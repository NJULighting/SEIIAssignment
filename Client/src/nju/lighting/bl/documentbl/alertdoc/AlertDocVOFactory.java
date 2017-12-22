package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.DocVOFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.alertdoc.AlertDocItemVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;

import java.util.List;

/**
 * Created on 2017/12/17.
 * Description:
 * @author Liao
 */
public class AlertDocVOFactory implements DocVOFactory {

    @Override
    public DocVO poToDocVO(DocPO po) {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        AlertDocPO alertDocPO = (AlertDocPO) po;
        List<AlertDocItemVO> itemVOList =
                VPOTransformer.toVPOList(alertDocPO.getItemPOS(),
                        itemPO -> new AlertDocItemVO(commodityInfo.getBasicCommodityItemVO(itemPO.getCommodityId()),
                                itemPO.getId(), itemPO.getCount()));
        return new AlertDocVO(po.getCreateTime(), po.getUserId(), po.getId(), po.getDocType(), itemVOList, alertDocPO.getComment(),
                alertDocPO.isTriggered(), alertDocPO.isExpired());
    }
}
