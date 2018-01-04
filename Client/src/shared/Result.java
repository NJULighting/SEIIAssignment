package shared;

/**
 * Created on 2017/12/29.
 * Description:
 * @author Liao
 */
public class Result<T> {
    private ResultMessage resultMessage;
    private T value;

    public Result(ResultMessage resultMessage, T value) {
        this.resultMessage = resultMessage;
        this.value = value;
    }

    public boolean hasValue() {
        return resultMessage.success();
    }

    public T getValue() {
        return value;
    }

    public ResultMessage getResultMessage() {
        return resultMessage;
    }
}
