package nju.lighting.bl.documentbl;

import nju.lighting.vo.DocVO;
import nju.lighting.po.doc.DocPO;

/**
 * Created on 2017/11/11.
 * Description:
 * @author Liao
 */
public abstract class Doc {
    /**
     * 审批单据
     */
    abstract void approve();

    /**
     * 创建相应的VO对象
     * @return 对应的<code>DocVO</code>
     */
    abstract DocVO createVO();

    /**
     * 创建响应的PO对象
     * @return 对应的<code>DocPO</code>
     */
    abstract DocPO createPO();
}
