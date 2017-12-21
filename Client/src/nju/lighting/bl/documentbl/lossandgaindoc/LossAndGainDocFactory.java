package nju.lighting.bl.documentbl.lossandgaindoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.DocFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainDocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainItemPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocItemVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocVO;

import java.util.List;

/**
 * Created on 2017/12/17.
 * Description:
 * @author Liao
 */
public class LossAndGainDocFactory implements DocFactory {
    @Override
    public Doc createDocForApproval(HistoryDocVO historyDocVO) {
        return null;
    }

    @Override
    public DocVO poToDocVO(DocPO po) {
        LossAndGainDocPO docPO = (LossAndGainDocPO) po;
        CommodityInfo commodityInfo = new CommodityInfoImpl();

        // Item list
        List<LossAndGainDocItemVO> itemVOList = VPOTransformer.toVPOList(docPO.getItemPOS(),
                itemPO -> new LossAndGainDocItemVO(commodityInfo.getBasicCommodityItemVO(itemPO.getCommodityId()),
                        itemPO.getCount(), itemPO.getType(), itemPO.getId()));

        return new LossAndGainDocVO(po.getCreateTime(), po.getUserId(), po.getId(), po.getDocType(),
                itemVOList, docPO.getComment());
    }
}
