package nju.lighting.bl.documentbl;

import nju.lighting.vo.DocVO;
import shared.ResultMessage;

/**
 * Created on 2017/11/7.
 * Description: 处理红冲相关的业务功能
 * @author Liao
 */
public class RedFlush {

    /**
     * 直接进行红冲操作
     * @param docVO 需要进行红冲的单据
     * @return 红冲的结果
     */
    public ResultMessage redFlush(DocVO docVO) {
        return null;
    }

    /**
     * 红冲并复制功能
     * @param target 需要进行红冲并复制的单据对象
     * @return 相应的进行红冲过的对象
     */
    public DocVO redFlushAndCopy(DocVO target) {
        return null;
    }
}
