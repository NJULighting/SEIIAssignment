package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.DocVOFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesReturnDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;

import java.util.List;

/**
 * Created on 2017/12/17.
 * Description:
 * @author Liao
 */
public class SalesReturnDocVOFactory implements DocVOFactory {
    @Override
    public Doc createDocForApproval(HistoryDocVO historyDocVO) {
        return null;
    }

    @Override
    public DocVO poToDocVO(DocPO po) {
        SalesReturnDocPO returnDoc = (SalesReturnDocPO) po;
        CommodityInfo commodityInfo = new CommodityInfoImpl();

        // Item list
        List<SalesDocItemVO> itemVOList = VPOTransformer.toVPOList(returnDoc.getItemPOS(),
                salesDocItemPO -> new SalesDocItemVO(commodityInfo.getBasicCommodityItemVO(salesDocItemPO.getCommodityID()),
                        salesDocItemPO.getNumber(), salesDocItemPO.getTotalAmount(), salesDocItemPO.getRemarks()));

        double beforeDiscountAmount = returnDoc.getFinalAmount() / (1 - returnDoc.getDiscount());

        return new SalesDocVO(po.getCreateTime(), po.getUserId(), po.getId(), po.getDocType(),
                Integer.parseInt(returnDoc.getCustomerId()), returnDoc.getSalesMan(), returnDoc.getRepository(),
                returnDoc.getRemarks(), beforeDiscountAmount, returnDoc.getDiscount(), returnDoc.getVoucher(),
                returnDoc.getFinalAmount(), itemVOList);
    }
}
