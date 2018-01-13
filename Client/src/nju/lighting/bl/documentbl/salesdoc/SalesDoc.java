package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerInfoImpl;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Create on 2017/11/12
 * Description:
 * 销售单
 * @author 高梦婷
 */
public class SalesDoc extends SalesTypeDoc {

    public SalesDoc(DocVO vo) {
        super(vo);
        assign(vo);
    }

    public SalesDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign(historyDocVO.getDocVO());
    }

    public SalesDoc(DocPO po) {
        super(po);
        SalesDocPO salesDocPO = (SalesDocPO) po;
        setAttributes(Integer.parseInt(salesDocPO.getCustomerId()), salesDocPO.getSalesMan(), salesDocPO.getRepository(),
                salesDocPO.getRemarks(), 0, salesDocPO.getDiscount(), salesDocPO.getVoucher(), salesDocPO.getFinalAmount());
        beforeDiscountAmount = calculateBeforeDiscount();

        itemList = new SaleDocItemList(SalesDocItemType.RETURN);
        salesDocPO.getItemPOS().forEach(itemList::add);
    }

    private void assign(DocVO vo) {
        SalesDocVO docVO = (SalesDocVO) vo;

        setAttributes(docVO.getCustomerId(), docVO.getSalesman(), docVO.getRepository(), docVO.getRemarks(),
                docVO.getBeforeDiscountAmount(), docVO.getDiscount(), docVO.getVoucher(), docVO.getFinalAmount());

        itemList = new SaleDocItemList(SalesDocItemType.SALE);
        docVO.getItems().forEach(itemList::add);
    }

    /**
     * Add Customer's account payable
     */
    @Override
    public ResultMessage approve() {
        ResultMessage res = itemList.approve();
        if (res != ResultMessage.SUCCESS)
            return res;

        // Change customer's payable
        CustomerInfo customerInfo = new CustomerInfoImpl();
        return customerInfo.changeReceivable(customerId, finalAmount);
    }

    public DocVO toVO() {
        return new SalesDocVO(createTime, userId, id, docType, customerId, salesman,
                repository, remarks, beforeDiscountAmount, discount, voucher, finalAmount, itemList.toVO());
    }

    public DocPO toPO() {
        return new SalesDocPO(id, docType, userId, createTime, checkTime, approvalComment, state,
                approvalId, salesman, Integer.toString(customerId), repository, remarks, discount,
                voucher, finalAmount, itemList.toPO(id));
    }

    public List<SalesDetailItemVO> getBusinessCondition() {
        return itemList.toBusinessConditionItemVO(createTime);
    }

    /**
     * Cost of this sale.(Sum of commodities' purchase price)
     */
    public double getTotalCost() {
        return itemList.getTotalCost();
    }
}
