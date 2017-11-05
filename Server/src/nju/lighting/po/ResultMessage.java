package nju.lighting.po;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public enum ResultMessage {
    SUCCESS, FAILURE;

    private String detail = "No detail";

    public String getDetail() {
        return detail;
    }

    public void addDetail(String detail) {
        this.detail = detail;
    }
}
