package shared;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public enum ResultMessage {
    SUCCESS, FAILURE, NETWORK_FAIL, INVALID_NAME, DUPLICATE;

    private String detail = "No detail";

    public String getDetail() {
        return detail;
    }

    public void addDetail(String detail) {
        this.detail = detail;
    }
}
