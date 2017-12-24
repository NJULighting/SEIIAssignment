package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

/**
 * Created on 2017/11/11.
 * Description:
 * 销售类单据，是销售单和销售退货单的父类
 * @author 高梦婷
 */
public abstract class SalesTypeDoc extends Doc {

    int customerId;
    String salesman;
    String repository;
    String remarks;
    double beforeDiscountAmount;
    double discount;
    double voucher;
    double finalAmount;
    SaleDocItemList itemList = new SaleDocItemList();

    SalesTypeDoc(DocVO vo) {
        super(vo);
    }

    SalesTypeDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
    }

    SalesTypeDoc(DocPO po) {
        super(po);
    }

    @Override
    public ResultMessage redFlush() {
        return null;
    }

    void setAttributes(int customerId, String salesman, String repository, String remarks,
                       double beforeDiscountAmount, double discount, double voucher, double finalAmount) {
        this.customerId = customerId;
        this.salesman = salesman;
        this.repository = repository;
        this.remarks = remarks;
        this.beforeDiscountAmount = beforeDiscountAmount;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
    }

    public String getSalesman() {
        return salesman;
    }

    public String getRemarks() {
        return remarks;
    }

    public double getDiscount() {
        return discount;
    }

    public double getVoucher() {
        return voucher;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    double calculateBeforeDiscount() {
        return (finalAmount + voucher) / (1 - discount);
    }

    public double getTotalAmount() {
        return itemList.getTotalRevenue();
    }

    public double getDiscountAmount() {
        return beforeDiscountAmount * discount;
    }

    public double getVoucherCausedRevenue() {
        double afterDiscount = beforeDiscountAmount * (1 - discount);
        return voucher > afterDiscount ? voucher - afterDiscount : 0;
    }

    @Override
    public String getRepository() {
        return repository;
    }

    @Override
    abstract public void approve();

    @Override
    abstract public ResultMessage reject();

    @Override
    abstract public ResultMessage modify();

    @Override
    public boolean containsCustomer(String customerId) {
        return this.customerId == Integer.parseInt(customerId);
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return itemList.containsCommodity(commodityName);
    }

    @Override
    public boolean containsRepository(String repository) {
        return this.repository.equals(repository);
    }

    @Override
    public String getCustomer() {
        return Integer.toString(customerId);
    }
}
