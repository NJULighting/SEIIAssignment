package shared;

public enum RepositoryChangeType {

    BUY("进货"),
    SELL("销售"),
    RETURN("退货"),
    BE_RETURN("被退货"), // 被退货
    LOSS("报损"),
    GAIN("报溢"),
    GIFT("赠品");

    private final String text;

    private RepositoryChangeType(String text){
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }
}
