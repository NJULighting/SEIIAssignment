package shared;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class BusinessHistoryFilter {
    private long startDate;

    private long endDate;

    private DocType type;

    private String customer;

    private String user;

    private String repository;

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

    public DocType getDocType() {
        return type;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
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

    public void setType(DocType type) {
        this.type = type;
    }

}