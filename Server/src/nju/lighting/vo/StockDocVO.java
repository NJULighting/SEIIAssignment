package nju.lighting.vo;

public class StockDocVO extends StockTypeDocVO {

    public StockDocVO(String stockTypeDocID, String customerId, String repository, String userId, String remarks, double totalAmount) {
        this.setStockTypeDocID(stockTypeDocID);
        this.setCustomerId(customerId);
        this.setRepository(repository);
        this.setUserId(userId);
        this.setRemarks(remarks);
        this.setTotalAmount(totalAmount);
    }

}
