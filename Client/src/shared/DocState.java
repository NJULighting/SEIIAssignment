package shared;

public enum DocState {

    UN_CHECKED("待审批"),
    DISAPPROVAL("审批不通过"),
    APPROVAL("审批通过");

    private final String text;

    private DocState(String text){
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }
}
