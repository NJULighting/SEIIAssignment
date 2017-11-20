package nju.lighting.bl.documentbl;

import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;
import shared.ResultMessage;

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

    protected Doc(String id, DocType docType, String userId, Date time) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.time = time;
    }

    /**
     * 通过单据，该方法应在审批单据时调用
     */
    abstract public void approve();

    /**
     *驳回单据，该方法在审批单据时调用
     */
    abstract public ResultMessage reject();

    /**
     *保存修改后的单据，该方法在审批单据时调用
     */

    abstract public ResultMessage modify();
    /**
     * 创建相应的VO对象
     * @return 对应的<code>DocVO</code>
     */
    abstract public DocVO createVO();

    /**
     * 创建响应的PO对象
     * @return 对应的<code>DocPO</code>
     */
    abstract public DocPO createPO();
}
