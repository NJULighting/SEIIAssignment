package nju.lighting.po;

public class SalesDocPO extends SalesTypeDocPO {

    public SalesDocPO(String ID, CustomerPO customer, String repository, UserPO operator, CommodityListPO commodityList, String remarks, double discount, double voucher) {
        this.setID(ID);
        this.setCustomer(customer);
        this.setRepository(repository);
        this.setOperator(operator);
        this.setCommodityList(commodityList);
        this.setRemarks(remarks);
        this.setDiscount(discount);
        this.setVoucher(voucher);
    }

}
