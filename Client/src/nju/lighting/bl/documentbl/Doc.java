package nju.lighting.bl.documentbl;

import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import shared.DocState;
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
    protected Date createTime;
    protected Date checkTime;
    protected String approvalComment;
    protected DocState state;
    protected String approvalId;

    protected Doc(String id, DocType docType, String userId, Date createTime) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.createTime = createTime;
    }

    protected Doc(DocPO po) {
        id = po.getId();
        docType = po.getDocType();
        userId = po.getUserId();
        createTime = po.getCreateTime();
        assignWithPO(po);
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
    abstract public DocVO toVO();

    /**
     * 创建响应的PO对象
     * @return 对应的<code>DocPO</code>
     */
    abstract public DocPO toPO();

    abstract protected void assignWithPO(DocPO po);
}
