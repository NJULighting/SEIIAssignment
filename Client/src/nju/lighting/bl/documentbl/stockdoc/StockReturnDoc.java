package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.stockdoc.StockDocPO;
import nju.lighting.po.doc.stockdoc.StockReturnDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.stockdoc.StockReturnDocVO;
import shared.ResultMessage;

/**
 * Create on 2017/11/12
 * Description:
 * 进货退货单
 * @author 高梦婷
 */
public class StockReturnDoc extends StockTypeDoc {

    public StockReturnDoc(DocVO vo) {
        super(vo);
        assign(vo);
    }

    public StockReturnDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign(historyDocVO.getDocVO());
    }

    public StockReturnDoc(DocPO po) {
        super(po);
        StockReturnDocPO stockDocPO = (StockReturnDocPO) po;
        setAttributes(stockDocPO.getCustomerId(), stockDocPO.getRepository(),
                stockDocPO.getRemarks(), stockDocPO.getTotalAmount());

        stockDocPO.getItemPOS().forEach(itemList::add);
    }

    private void assign(DocVO vo) {
        StockReturnDocVO docVO = (StockReturnDocVO) vo;
        setAttributes(docVO.getCustomerId(), docVO.getRepository(), docVO.getRemarks(), docVO.getTotalAmount());

        docVO.getItems().forEach(itemList::add);
    }

    /**
     * 审批单据，减少客户应收
     */
    @Override
    public void approve() {
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        return new StockReturnDocVO(createTime, userId, id, customerId,
                repository, remarks, totalAmount, itemList.toVO());
    }

    @Override
    public DocPO toPO() {
        return new StockReturnDocPO(id, docType, userId, createTime, checkTime, approvalComment, state,
                approvalId, customerId, repository, remarks, totalAmount, itemList.toPO(id));
    }

    public double getRevenue() {
        return itemList.getRevenue();
    }
}
