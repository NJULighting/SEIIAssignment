package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

/**
 * Created on 2017/11/11.
 * Description:
 * 进货类单据，是进货单和进货退货单的父类
 * @author 高梦婷
 */
abstract public class StockTypeDoc extends Doc {

    String customerId;
    String repository;
    String remarks;
    double totalAmount;
    StockDocItemList itemList = new StockDocItemList();


    StockTypeDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
    }

    StockTypeDoc(DocPO po) {
        super(po);
    }

    StockTypeDoc(DocVO vo) {
        super(vo);
    }

    void setAttributes(String customerId, String repository, String remarks, double totalAmount) {
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
    }

    @Override
    public String getCustomer() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getTotalAmount() {
        return totalAmount;
    }


    @Override
    abstract public void approve();

    @Override
    abstract public ResultMessage reject();

    @Override
    public ResultMessage redFlush() {
        return null;
    }

    @Override
    abstract public ResultMessage modify();

    @Override
    abstract public DocVO toVO();

    @Override
    abstract public DocPO toPO();

    @Override
    public boolean containsCustomer(String customerId) {
        return this.customerId.equals(customerId);
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return itemList.containsCommodity(commodityName);
    }

    @Override
    public boolean containsRepository(String repository) {
        return this.repository.equals(repository);
    }

}
