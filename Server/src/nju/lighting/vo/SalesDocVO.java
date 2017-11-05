package nju.lighting.vo;


public class SalesDocVO extends SalesTypeDocVO{

    public SalesDocVO(String ID, CustomerVO customer, String repository, UserVO operator, CommodityListVO commodityList, String remarks, double discount, double voucher){
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
