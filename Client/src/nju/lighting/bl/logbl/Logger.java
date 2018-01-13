package nju.lighting.bl.logbl;

import shared.OPType;

/**
 * Created on 2017/11/5.
 * Description:
 * @author Liao
 */
public interface Logger {

    /**
     * 添加日志
     * @param type 此次日志的操作类型
     * @param message 次此日志的信息
     */
    void add(OPType type, String message);

    /**
     * 添加日志
     * @param type 次此日志的操作类型
     * @param object 此次操作对象
     */
    void add(OPType type, Object object);
}
