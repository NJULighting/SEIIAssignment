package nju.lighting.vo.doc.historydoc;

import nju.lighting.vo.DocVO;
import shared.DocType;
import shared.HistoryDocType;

import java.util.Date;

public class HistoryDocVO {

    private String creatorId;

    private DocVO docVO;

    private String comment;

    private HistoryDocType state;

    public HistoryDocVO(Date time, String creatorId, String docId,
                        DocType type, DocVO docVO, String comment,
                        HistoryDocType state) {

        this.docVO = docVO;
        this.comment = comment;
        this.state = state;
    }

    /*
    总经理审批单据时用的构造函数，确定了historyDoc的 审批人ID，评语以及doc
    审批时间在bl层生成
     */
    public HistoryDocVO(String creatorId, String comment, DocVO docVO) {
        this.creatorId = creatorId;
        this.comment = comment;
        this.docVO=docVO;
    }

    public DocVO getDocVO() {
        return docVO;
    }

    public void setDocVO(DocVO docVO) {
        this.docVO = docVO;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public HistoryDocType getState() {
        return state;
    }

    public void setState(HistoryDocType state) {
        this.state = state;
    }
}
