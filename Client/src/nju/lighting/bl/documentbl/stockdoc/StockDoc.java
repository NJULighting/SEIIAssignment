package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerInfoImpl;
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
public class StockDoc extends StockTypeDoc {

    public StockDoc(DocVO vo) {
        super(vo);
        assign(vo);
    }

    public StockDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign(historyDocVO.getDocVO());
    }

    public StockDoc(DocPO po) {
        super(po);
        StockDocPO stockDocPO = (StockDocPO) po;
        setAttributes(stockDocPO.getCustomerId(), stockDocPO.getCustomerId(),
                stockDocPO.getRepository(), stockDocPO.getTotalAmount());

        itemList = new StockDocItemList(StockDocItemType.STOCK);
        stockDocPO.getItemPOS().forEach(itemList::add);
    }

    private void assign(DocVO vo) {
        StockDocVO docVO = (StockDocVO) vo;
        setAttributes(docVO.getCustomerId(), docVO.getRepository(), docVO.getRemarks(), docVO.getTotalAmount());

        itemList = new StockDocItemList(StockDocItemType.STOCK);
        docVO.getItems().forEach(itemList::add);
    }

    /**
     * 审批单据，增加客户应收，增加商品数量
     */
    @Override
    public ResultMessage approve() {
        ResultMessage res = itemList.approve();
        if (res != ResultMessage.SUCCESS)
            return res;

        // Change customer's receivable
        CustomerInfo customerInfo = new CustomerInfoImpl();
        return customerInfo.changeReceivable(Integer.parseInt(customerId), totalAmount);
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        return new StockDocVO(createTime, userId, id, customerId, repository,
                remarks, totalAmount, itemList.toVO());
    }

    @Override
    public DocPO toPO() {
        return new StockDocPO(id, docType, userId, createTime, checkTime, approvalComment,
                state, approvalId, customerId, repository, remarks, totalAmount, itemList.toPO(id));
    }
}
