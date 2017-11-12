package nju.lighting.bl.documentbl;

import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;

/**
 * Created on 2017/11/11.
 * Description: Doc的领域模型
 * @author Liao
 */
public abstract class Doc {

    protected String id;
    protected DocType docType;
    protected String userId;
    protected Date time;

    Doc(String id, DocType docType, String userId, Date time) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.time = time;
    }

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
