package nju.lighting.vo.doc.historydoc;

import nju.lighting.vo.DocVO;
import shared.DocType;
import shared.HistoryDocType;

import java.util.Date;

public class HistoryDocVO extends DocVO {

    private DocVO docVO;

    private String comment;

    private HistoryDocType state;

    public HistoryDocVO(Date time, String creatorId, String docId,
                        DocType type, DocVO docVO, String comment,
                        HistoryDocType state) {
        super(time, creatorId, docId, type);
        this.docVO = docVO;
        this.comment = comment;
        this.state = state;
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
