package nju.lighting.vo;

public class SalesReturnDocVO extends SalesTypeDocVO {

    public SalesReturnDocVO(){}

    /**
     *
     * @param SalesTypeDocID
     * 销售退货单的ID，格式为XSTHD-yyyyMMdd-xxxxx
     * @param customerId
     * 客户的编号
     * @param salesman
     * 客户的默认业务员
     * @param repository
     * 默认的仓库的名字
     * @param userId
     * 系统使用者的ID
     * @param remarks
     * 销售单备注
     * @param beforeDiscountAmount
     * 折让前总额，所有附属此销售单的SalesDocItem的总额之和
     * @param discount
     * 折让，不可超出操作员被允许设置的折让
     * @param voucher
     * 代金券金额
     */
    public SalesReturnDocVO(String SalesTypeDocID, String customerId, String salesman, String repository, String userId, String remarks, double beforeDiscountAmount, double discount, double voucher) {
        this.setSalesTypeDocID(SalesTypeDocID);
        this.setCustomerId(customerId);
        this.setSalesman(salesman);
        this.setRepository(repository);
        this.setUserId(userId);
        this.setRemarks(remarks);
        this.setBeforeDiscountAmount(beforeDiscountAmount);
        this.setDiscount(discount);
        this.setVoucher(voucher);
    }

}
