package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.stockdoc.StockDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.stockdoc.StockDocVO;
import shared.ResultMessage;

/**
 * Create on 2017/11/12
 * Description:
 * 进货单
 * @author 高梦婷
 */
class StockDoc extends StockTypeDoc {

    StockDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        StockDocVO docVO = (StockDocVO) historyDocVO.getDocVO();
        setAttributes(docVO.getCustomerId(), docVO.getRepository(), docVO.getRemarks(), docVO.getTotalAmount());

        docVO.getItems().forEach(itemList::add);
    }

    StockDoc(DocPO po) {
        super(po);
        StockDocPO stockDocPO = (StockDocPO) po;
        setAttributes(stockDocPO.getCustomerId(), stockDocPO.getCustomerId(),
                stockDocPO.getRepository(), stockDocPO.getTotalAmount());

        stockDocPO.getItemPOS().forEach(itemList::add);
    }

    /**
     * 审批单据，增加客户应收，增加商品数量
     */
    @Override
    public void approve() {
    }

    @Override
    public ResultMessage reject() {
        return null;
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        return null;
    }

    @Override
    public DocPO toPO() {
        return new StockDocPO(id, docType, userId, createTime, checkTime, approvalComment,
                state, approvalId, customerId, repository, remarks, totalAmount, itemList.toPO(id));
    }
}
