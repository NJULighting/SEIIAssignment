package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.DocVOFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
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
public class SalesDocVOFactory implements DocVOFactory {

    @Override
    public Doc createDocForApproval(HistoryDocVO historyDocVO) {
        return null;
    }

    @Override
    public DocVO poToDocVO(DocPO po) {
        SalesDocPO salesDocPO = (SalesDocPO) po;
        CommodityInfo commodityInfo = new CommodityInfoImpl();

        // Item list
        List<SalesDocItemVO> itemVOList = VPOTransformer.toVPOList(salesDocPO.getItemPOS(),
                itemPO -> new SalesDocItemVO(itemPO.getId(), commodityInfo.getBasicCommodityItemVO(itemPO.getCommodityID()),
                        itemPO.getNumber(), itemPO.getTotalAmount(), itemPO.getRemarks()));

        double beforeDiscountAmount = salesDocPO.getFinalAmount() / (1 - salesDocPO.getDiscount());

        return new SalesDocVO(po.getCreateTime(), po.getUserId(), po.getId(), po.getDocType(),
                Integer.parseInt(salesDocPO.getCustomerId()), salesDocPO.getSalesMan(), salesDocPO.getRepository(),
                salesDocPO.getRemarks(), beforeDiscountAmount, salesDocPO.getDiscount(), salesDocPO.getVoucher(),
                salesDocPO.getFinalAmount(), itemVOList);
    }
}
