package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesReturnDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.salesdoc.SalesReturnDocVO;
import shared.ResultMessage;

/**
 * Create on 2017/11/12
 * Description:
 * 销售退货单
 * @author 高梦婷
 */
public class SalesReturnDoc extends SalesTypeDoc {

    public SalesReturnDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        SalesReturnDocVO docVO = (SalesReturnDocVO) historyDocVO.getDocVO();
        setAttributes(docVO.getCustomerId(), docVO.getSalesman(), docVO.getRepository(), docVO.getRemarks(),
                docVO.getBeforeDiscountAmount(), docVO.getDiscount(), docVO.getVoucher(), docVO.getFinalAmount());
    }

    public SalesReturnDoc(DocPO po) {
        super(po);
        SalesReturnDocPO docPO = (SalesReturnDocPO) po;
        setAttributes(Integer.parseInt(docPO.getCustomerId()), docPO.getSalesMan(), docPO.getRepository(),
                docPO.getRemarks(), 0.0, docPO.getDiscount(), docPO.getVoucher(), docPO.getFinalAmount());
        beforeDiscountAmount = calculateBeforeDiscount();
    }

    /**
     * Reduce customer's receivable
     */
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
        return new SalesReturnDocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, salesman, Integer.toString(customerId), repository,
                remarks, discount, voucher, finalAmount, itemList.toPO(id));
    }
}
