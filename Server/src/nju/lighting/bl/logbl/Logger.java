package nju.lighting.bl.logbl;

import nju.lighting.vo.UserVO;

/**
 * Created on 2017/11/5.
 * Description:
 * @author Liao
 */
public interface Logger {
    void add(OPType type, String itemName, String id);

    void add(DocDescription description);
}
