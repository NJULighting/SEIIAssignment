package shared;

import java.io.Serializable;

public class TwoTuple<T, R> implements Serializable {

    public static final long serialVersionUID = 1230458482314L;

    public T t;

    public R r;

    public TwoTuple(T t, R r) {
        this.t = t;
        this.r = r;
    }

    public TwoTuple() {
    }
}
