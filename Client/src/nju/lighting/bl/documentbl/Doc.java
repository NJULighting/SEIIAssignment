package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import shared.DocState;
import shared.DocType;
import shared.ResultMessage;

import java.util.Date;
import java.util.Optional;

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

    @Deprecated
    protected Doc(String id, DocType docType, String userId, Date createTime) {
        this.id = id;
        this.docType = docType;
        this.userId = userId;
        this.createTime = createTime;
    }

    /**
     * Constructor for red flush.(only)
     */
    protected Doc(DocVO docVO) {
        id = docVO.getDocId();
        docType = docVO.getType();
        userId = docVO.getCreatorId();
        createTime = docVO.getTime();
    }

    /**
     * Constructor for approval module. The basic information should
     * initialized in presentation layer
     */
    protected Doc(HistoryDocVO historyDocVO) {
        this(historyDocVO.getDocVO());
        checkTime = historyDocVO.getCheckTime();
        approvalComment = historyDocVO.getComment();
        state = historyDocVO.getState();
        approvalId = historyDocVO.getApprover().getID();
        userId = historyDocVO.getDocVO().getCreatorId();
    }

    /**
     * Constructor for po
     */
    protected Doc(DocPO po) {
        id = po.getId();
        docType = po.getDocType();
        userId = po.getUserId();
        createTime = po.getCreateTime();
        checkTime = po.getCheckTime();
        approvalComment = po.getApprovalComment();
        state = po.getState();
        approvalId = po.getApprovalId();
    }

    /**
     * Invoke this method when approve a document. This method
     * will change other module's information in this method.
     */
    abstract protected ResultMessage approve();

    /**
     * Basic red flush method. It will change the create time, state
     * of doc and creator's id.
     */
    void executeRedFlush() {
        createTime = new Date();
        state = DocState.UN_CHECKED;

        UserInfo userInfo = new UserInfoImpl();
        userId = userInfo.getIDOfSignedUser();

        redFlush();
    }

    abstract protected void redFlush();

    /**
     * 保存修改后的单据，该方法在审批单据时调用
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

    abstract public boolean containsCustomer(String customerId);

    abstract public boolean containsCommodity(String commodityName);

    abstract public boolean containsRepository(String repository);

    abstract public String getCustomer();

    abstract public String getRepository();

    HistoryDocVO toHistoryDocVO() {
        UserInfo userInfo = new UserInfoImpl();
        UserVO approver = Optional.ofNullable(approvalId).map(userInfo::getUserVOByID).orElse(null);
        return new HistoryDocVO(userInfo.getUserVOByID(userId), toVO(), approvalComment, state,
                checkTime, approver);
    }

    BusinessHistoryItemVO toBusinessHistoryItemVO() {
        return new BusinessHistoryItemVO(createTime, docType, toVO(), getCustomer(), userId, getRepository());
    }

    public String getId() {
        return id;
    }

    public DocType getDocType() {
        return docType;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DocState getState() {
        return state;
    }

    public void reject() {
        state = DocState.DISAPPROVAL;
    }
}
