package nju.lighting.vo.doc.historydoc;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import shared.DocState;

import java.util.Date;

public class HistoryDocVO {

    private UserVO creator;
    private DocVO docVO;
    private String comment;
    private DocState state;
    private Date checkTime;
    private UserVO approver;

    /**
     * Constructor for bl
     */
    public HistoryDocVO(UserVO creator, DocVO docVO, String comment, DocState state,
                        Date checkTime, UserVO approver) {
        this.creator = creator;
        this.docVO = docVO;
        this.comment = comment;
        this.state = state;
        this.checkTime = checkTime;
        this.approver = approver;
    }

    /**
     * Constructor for approval pre
     */
    public HistoryDocVO(UserVO creator, String comment, DocVO docVO) {
        this.creator = creator;
        this.comment = comment;
        this.docVO = docVO;
        checkTime = new Date();
    }

    public DocVO getDocVO() {
        return docVO;
    }

    public String getComment() {
        return comment;
    }

    public DocState getState() {
        return state;
    }

    public UserVO getCreator() {
        return creator;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public UserVO getApprover() {
        return approver;
    }
}
