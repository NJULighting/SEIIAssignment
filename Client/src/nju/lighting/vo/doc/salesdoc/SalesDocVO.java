package nju.lighting.vo.doc.salesdoc;

import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class SalesDocVO extends DocVO {

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
     * Constructor for pre
     */
    public SalesDocVO(Date time, String creatorId, int customerId,
                      String salesman, String repository, String remarks,
                      double discount, double voucher, List<SalesDocItemVO> items) {
        super(time, DocType.SALES, creatorId);
        this.customerId = customerId;
        this.salesman = salesman;
        this.repository = repository;
        this.remarks = remarks;
        this.items = items;

        beforeDiscountAmount = calculateBeforeDiscount();
        this.discount = discount;
        this.voucher = voucher;
        finalAmount = beforeDiscountAmount - discount - voucher;
    }

    /**
     * Constructor for bl
     */
    public SalesDocVO(Date time, String creatorId, String docId, DocType type,
                      int customerId, String salesman, String repository, String remarks,
                      double beforeDiscountAmount, double discount, double voucher, double finalAmount,
                      List<SalesDocItemVO> items) {
        super(time, creatorId, docId, type);
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

    @Deprecated
    public SalesDocVO(Date time, String creatorId, String docId, DocType type,
                      int customerId, String salesman,
                      String repository, String remarks,
                      double beforeDiscountAmount, double discount,
                      double voucher, double finalAmount) {
        super(time, creatorId, docId, type);
        this.customerId = customerId;
        this.salesman = salesman;
        this.repository = repository;
        this.remarks = remarks;
        this.beforeDiscountAmount = beforeDiscountAmount;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
    }

    private double calculateBeforeDiscount() {
        return items.stream().mapToDouble(SalesDocItemVO::getTotalAmount).sum();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getSalesman() {
        return salesman;
    }

    public String getRepository() {
        return repository;
    }

    public String getRemarks() {
        return remarks;
    }

    public double getBeforeDiscountAmount() {
        return beforeDiscountAmount;
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

    public List<SalesDocItemVO> getItems() {
        return items;
    }

    @Override
    public DocPO toPO() {
        List<SalesDocItemPO> poList = CollectionTransformer.toList(items, SalesDocItemVO::toPO);
        return new SalesDocPO(getType(), getCreatorId(), getTime(), salesman,
                customerId + "", repository, remarks, discount,
                voucher, finalAmount, poList);
    }
}
