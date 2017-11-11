package nju.lighting.bl.logbl;

import shared.OPType;

/**
 * Created on 2017/11/5.
 * Description:
 * @author Liao
 */
public interface Logger extends DocLogger, OtherLogger {

    void add(OPType type, String itemName, String id);

    void add(OPType type, Describable description);
}
