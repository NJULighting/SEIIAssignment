package nju.lighting.bl.logbl;

import shared.OPType;

/**
 * Created on 2017/11/5.
 * Description:
 * @author Liao
 */
public interface Logger {

    void add(OPType type, String message);

    void add(OPType type, Object object);
}
