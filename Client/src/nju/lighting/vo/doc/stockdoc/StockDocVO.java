package nju.lighting.vo.doc.stockdoc;

import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.stockdoc.StockDocItemPO;
import nju.lighting.po.doc.stockdoc.StockDocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;
import java.util.List;

public class StockDocVO extends DocVO {

    private String customerId;
    private String repository;
    private String remarks = "";
    private double totalAmount;
    private List<StockDocItemVO> items;

    /**
     * Constructor for pre
     */
    public StockDocVO(Date time, String creatorId, String customerId,
                      String repository, String remarks, double totalAmount, List<StockDocItemVO> items) {
        super(time, DocType.STOCK, creatorId);
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    /**
     * Constructor for bl
     */
    public StockDocVO(Date time, String creatorId, String docId,
                      String customerId, String repository, String remarks, double totalAmount,
                      List<StockDocItemVO> items) {
        super(time, creatorId, docId, DocType.STOCK);
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public DocPO toPO() {
        List<StockDocItemPO> itemPOList = VPOTransformer.toVPOList(items, StockDocItemVO::toPO);
        return new StockDocPO(getType(), getCreatorId(), getTime(), customerId, repository,
                remarks, totalAmount, itemPOList);
    }
}
