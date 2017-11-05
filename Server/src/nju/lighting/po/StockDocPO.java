package nju.lighting.po;

public class StockDocPO extends StockTypeDocPO {

    public StockDocPO(String ID, CustomerPO supplier, String repository, UserPO operator, CommodityListPO commodityList, String remarks) {
        this.setID(ID);
        this.setSupplier(supplier);
        this.setRepository(repository);
        this.setOperator(operator);
        this.setCommodityList(commodityList);
        this.setRemarks(remarks);
    }

}
