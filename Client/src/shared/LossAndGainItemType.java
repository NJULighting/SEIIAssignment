package shared;

public enum LossAndGainItemType {

    LOSS("报损"),
    GAIN("报溢");

    private final String text;

    private LossAndGainItemType(String text){
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }
}
