package shared;

public enum CustomerType {
    SUPPLIER("供应商"),
    SALESPERSON("销售商"),
    ALL("所有"); // This type should only be used in methods for searching

    private final String text;

    private CustomerType(String string){
        text=string;
    }


    @Override
    public String toString() {
        return text;
    }
}
