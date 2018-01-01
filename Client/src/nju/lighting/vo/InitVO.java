package nju.lighting.vo;

import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class InitVO {
    private final int id;
    private final Date time;
    private String url;
    private final UserVO userVO;

    // for pre
    public InitVO( Date time, String url, UserVO userVO) {
        this.id = 0;
        this.time = time;
        this.url = url;
        this.userVO = userVO;
    }

    public InitVO(int id, Date time, String url, UserVO userVO) {
        this.id = id;
        this.time = time;
        this.url = url;
        this.userVO = userVO;
    }

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public UserVO getUserVO() {
        return userVO;
    }
}
