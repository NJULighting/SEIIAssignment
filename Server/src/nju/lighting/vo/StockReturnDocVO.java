package nju.lighting.vo;

import nju.lighting.vo.StockTypeDocVO;

public class StockReturnDocVO extends StockTypeDocVO {

    public StockReturnDocVO(String stockTypeDocID, String customerId, String repository, String userId, String remarks, double totalAmount) {
        this.setStockTypeDocID(stockTypeDocID);
        this.setCustomerId(customerId);
        this.setRepository(repository);
        this.setUserId(userId);
        this.setRemarks(remarks);
        this.setTotalAmount(totalAmount);
    }

}
