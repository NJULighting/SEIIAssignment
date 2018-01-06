package shared;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public enum Identity {

    GENERAL("总经理"),
    FINANCE("财务人员"),
    REPOSITORY("库存人员"),
    SALE("销售人员"),
    SALE_MANAGER("销售经理"),
    SYSTEM_ADMIN("系统管理员");

    private final String text;

    private Identity(String text){
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }
}
