package shared;

/**
 * Created on 2017/10/18.
 * Description:
 * @author Liao
 */
public enum CostDocItemType {
    ACCOMMODATION("住宿"),
    TRANSPORTATION("交通"),
    RECEPTOIN("收据"),
    COMMUNICATION("交流"),
    MEETING("会议"),
    BUSINESS_TRIP("出差"),
    OFFICE("办公"),
    MEAL("饮食"),
    OTHER("其他");

    private final String text;

    private CostDocItemType(String text){
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }
}
