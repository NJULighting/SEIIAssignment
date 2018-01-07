package shared;

public enum DocType {
    ALL("所有"),
    LOSS_AND_GAIN("报损报溢单"),
    ALERT("报警单"),
    ACCOUNT_IN("收款单"),
    ACCOUNT_OUT("付款单"),
    COST("现金费用单"),
    GIFT("赠品单"),
    SALES("销售单"),
    SALES_RETURN("销售退货单"),
    STOCK("进货单"),
    STOCK_RETURN("进货退货单");



    private final String text;

    private DocType(final String text){
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }
}
