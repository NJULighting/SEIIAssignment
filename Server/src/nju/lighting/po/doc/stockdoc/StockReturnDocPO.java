package nju.lighting.po.doc.stockdoc;

public class StockReturnDocPO extends StockTypeDocPO {

    public StockReturnDocPO(String stockTypeDocID, String customerId, String repository, String userId, String remarks, double totalAmount) {
        this.setStockTypeDocID(stockTypeDocID);
        this.setCustomerId(customerId);
        this.setRepository(repository);
        this.setUserId(userId);
        this.setRemarks(remarks);
        this.setTotalAmount(totalAmount);
    }

}
