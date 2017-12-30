package shared;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public enum ResultMessage {
    SUCCESS, FAILURE, NETWORK_FAIL, INVALID_NAME, INVALID_ID, DUPLICATE;

    public boolean success() {
        return this == SUCCESS;
    }
}
