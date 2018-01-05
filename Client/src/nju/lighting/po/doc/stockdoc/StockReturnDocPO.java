package nju.lighting.po.doc.stockdoc;

import nju.lighting.po.doc.DocPO;
import shared.DocState;
import shared.DocType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StockReturnDocPO extends DocPO implements Serializable {

    private static final long serialVersionUID = -606497910496800338L;
    private String customerId;
    private String repository;
    private String remarks;
    private double totalAmount = 0;
    private List<StockDocItemPO> itemPOS;


    /**
     * Constructor for approval module.
     */
    public StockReturnDocPO(String id, DocType docType, String userId, Date createTime,
                            Date checkTime, String approvalComment, DocState state, String approvalId,
                            String customerId, String repository, String remarks, double totalAmount, List<StockDocItemPO> itemPOS) {
        super(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId);
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
        this.itemPOS = itemPOS;
    }

    /**
     * Constructor for committing a new document.
     */
    public StockReturnDocPO(DocType type, String userId, Date createTime, String customerId,
                            String repository, String remarks, double totalAmount, List<StockDocItemPO> itemPOS) {
        super(type, userId, createTime);
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
        this.itemPOS = itemPOS;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRepository() {
        return repository;
    }

    public String getRemarks() {
        return remarks;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<StockDocItemPO> getItemPOS() {
        return itemPOS;
    }
}
