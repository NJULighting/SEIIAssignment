package shared;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class SaleRecordFilter {
    private long startDate;

    private long endDate;

    private String commodity;

    private String customer;

    private String user;


    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getCommodity() {
        return commodity;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setType(String commodity) {
        this.commodity = commodity;
    }


}
