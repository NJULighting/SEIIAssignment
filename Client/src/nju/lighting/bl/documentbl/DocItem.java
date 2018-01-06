package nju.lighting.bl.documentbl;

import shared.ResultMessage;

/**
 * Created on 2017/12/24.
 * Description:
 * @author Liao
 */
public interface DocItem {
    void redFlush();

    ResultMessage approve();

    void setId(int id);
}
