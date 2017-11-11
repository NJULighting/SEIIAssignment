package nju.lighting.bl.logbl;

import shared.OPType;

/**
 * Created on 2017/11/7.
 * Description: 对单据的操作进行日志记录的接口
 * @author Liao
 */
public interface DocLogger {
    /**
     * 对添加、删除、修改、审批单据的操作进行记录
     * @param type        操作的种类
     * @param description 实现了<code>Describable</code>接口的单据类
     */
    void add(OPType type, Describable description);
}
