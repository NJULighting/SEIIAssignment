package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerInfoImpl;
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

    public SalesReturnDoc(DocVO vo) {
        super(vo);
        assign(vo);
    }

    public SalesReturnDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign(historyDocVO.getDocVO());
    }

    public SalesReturnDoc(DocPO po) {
        super(po);
        SalesReturnDocPO docPO = (SalesReturnDocPO) po;
        setAttributes(Integer.parseInt(docPO.getCustomerId()), docPO.getSalesMan(), docPO.getRepository(),
                docPO.getRemarks(), 0.0, docPO.getDiscount(), docPO.getVoucher(), docPO.getFinalAmount());
        beforeDiscountAmount = calculateBeforeDiscount();

        itemList = new SaleDocItemList(SalesDocItemType.RETURN);
        docPO.getItemPOS().forEach(itemList::add);
    }

    private void assign(DocVO vo) {
        SalesReturnDocVO docVO = (SalesReturnDocVO) vo;
        setAttributes(docVO.getCustomerId(), docVO.getSalesman(), docVO.getRepository(), docVO.getRemarks(),
                docVO.getBeforeDiscountAmount(), docVO.getDiscount(), docVO.getVoucher(), docVO.getFinalAmount());

        itemList = new SaleDocItemList(SalesDocItemType.RETURN);
        docVO.getItems().forEach(itemList::add);
    }

    /**
     * Reduce customer's receivable
     */
    @Override
    public ResultMessage approve() {
        ResultMessage res = itemList.approve();
        if (res != ResultMessage.SUCCESS)
            return res;

        // Change Customers receivable
        CustomerInfo customerInfo = new CustomerInfoImpl();
        return customerInfo.changePayable(customerId, finalAmount);
    }

    @Override
    public DocVO toVO() {
        return new SalesReturnDocVO(createTime, userId, id, customerId, salesman, repository,
                remarks, beforeDiscountAmount, discount, voucher, finalAmount, itemList.toVO());
    }

    @Override
    public DocPO toPO() {
        return new SalesReturnDocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, salesman, Integer.toString(customerId), repository,
                remarks, discount, voucher, finalAmount, itemList.toPO(id));
    }
}
