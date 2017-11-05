package nju.lighting.po;

public class StockReturnDocPO extends StockTypeDocPO {

    public StockReturnDocPO(String ID, CustomerPO supplier, String repository, UserPO operator, CommodityListPO commodityList, String remarks) {
        this.setID(ID);
        this.setSupplier(supplier);
        this.setRepository(repository);
        this.setOperator(operator);
        this.setCommodityList(commodityList);
        this.setRemarks(remarks);
    }

}
