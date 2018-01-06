package shared;

import java.io.Serializable;

/**
 * Created on 2017/12/29.
 * Description:
 * @author Liao
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -4980835562699803011L;
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
