package nju.lighting.vo.doc.salesdoc;

import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.po.doc.salesdoc.SalesReturnDocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class SalesReturnDocVO extends DocVO {

    private int customerId;
    private String salesman;
    private String repository;
    private String remarks;
    private double beforeDiscountAmount;
    private double discount;
    private double voucher;
    private double finalAmount;
    private List<SalesDocItemVO> items;

    /**
     * Constructor for bl
     */
    public SalesReturnDocVO(Date time, String creatorId, String docId,
                            int customerId, String salesman, String repository, String remarks,
                            double beforeDiscountAmount, double discount, double voucher, double finalAmount,
                            List<SalesDocItemVO> items) {
        super(time, creatorId, docId, DocType.SALES_RETURN);
        this.customerId = customerId;
        this.salesman = salesman;
        this.repository = repository;
        this.remarks = remarks;
        this.beforeDiscountAmount = beforeDiscountAmount;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
        this.items = items;
    }

    /**
     * Constructor for pre
     */
    public SalesReturnDocVO(Date time, String creatorId, int customerId,
                            String salesman, String repository, String remarks,
                            double discount, double voucher, List<SalesDocItemVO> items) {
        super(time, DocType.SALES_RETURN, creatorId);
        this.customerId = customerId;
        this.salesman = salesman;
        this.repository = repository;
        this.remarks = remarks;
        this.items = items;
        this.beforeDiscountAmount = calculateBeforeDiscount();
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = beforeDiscountAmount  - discount - voucher;
    }

    private double calculateBeforeDiscount() {
        return items.stream().mapToDouble(SalesDocItemVO::getTotalAmount).sum();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

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

    public double getBeforeDiscountAmount() {
        return beforeDiscountAmount;
    }

    public void setBeforeDiscountAmount(double beforeDiscountAmount) {
        this.beforeDiscountAmount = beforeDiscountAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.beforeDiscountAmount = beforeDiscountAmount;
    }

    public List<SalesDocItemVO> getItems() {
        return items;
    }

    @Override
    public DocPO toPO() {
        List<SalesDocItemPO> poList = CollectionTransformer.toList(items, SalesDocItemVO::toPO);
        return new SalesReturnDocPO(getType(), getCreatorId(), getTime(), salesman, customerId + "", repository,
                remarks, discount, voucher, finalAmount, poList);
    }
}
