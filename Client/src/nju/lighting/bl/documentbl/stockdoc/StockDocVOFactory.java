package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.DocVOFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.stockdoc.StockDocItemPO;
import nju.lighting.po.doc.stockdoc.StockDocPO;
import nju.lighting.po.doc.stockdoc.StockReturnDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.stockdoc.StockDocItemVO;
import nju.lighting.vo.doc.stockdoc.StockDocVO;
import nju.lighting.vo.doc.stockdoc.StockReturnDocVO;
import shared.DocType;

import java.util.List;

/**
 * Created on 2017/12/17.
 * Description:
 * @author Liao
 */
public class StockDocVOFactory implements DocVOFactory {
    private CommodityInfo commodityInfo = new CommodityInfoImpl();

    @Override
    public Doc createDocForApproval(HistoryDocVO historyDocVO) {
        return null;
    }

    @Override
    public DocVO poToDocVO(DocPO po) {
        if (po.getDocType() == DocType.STOCK) {
            return getStockDocVO(po);
        } else
            return getStockReturnDocVO(po);
    }

    private DocVO getStockDocVO(DocPO po) {
        StockDocPO stockDocPO = (StockDocPO) po;

        // Item list
        List<StockDocItemVO> voItemList = getItemVOList(po);

        return new StockDocVO(po.getCreateTime(), po.getUserId(), po.getId(), stockDocPO.getCustomerId(),
                stockDocPO.getRepository(), stockDocPO.getRemarks(), stockDocPO.getTotalAmount(),
                voItemList);
    }

    private DocVO getStockReturnDocVO(DocPO po) {
        StockReturnDocPO returnDocPO = (StockReturnDocPO) po;

        // Item list
        List<StockDocItemVO> itemVOList = getItemVOList(po);

        return new StockReturnDocVO(po.getCreateTime(), po.getUserId(), po.getId(), returnDocPO.getCustomerId(),
                returnDocPO.getRepository(), returnDocPO.getRemarks(), returnDocPO.getTotalAmount(), itemVOList);
    }

    private List<StockDocItemVO> getItemVOList(DocPO po) {
        List<StockDocItemPO> itemPOList = po.getDocType() == DocType.STOCK ?
                ((StockDocPO) po).getItemPOS() : ((StockReturnDocPO) po).getItemPOS();

        return VPOTransformer.toVPOList(itemPOList,
                stockDocItemPO -> new StockDocItemVO(stockDocItemPO.getId(), commodityInfo.getBasicCommodityItemVO(stockDocItemPO.getCommodityID()),
                        stockDocItemPO.getNumber(), stockDocItemPO.getTotalAmount(), stockDocItemPO.getRemarks()));
    }
}
