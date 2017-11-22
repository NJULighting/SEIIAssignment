package nju.lighting.po.doc.historydoc;

import nju.lighting.po.doc.DocPO;
import shared.DocType;
import shared.HistoryDocType;

import java.util.Date;

public class HistoryDocPO extends DocPO {

    private String docId;

    private String comment;

    private HistoryDocType state;

    public HistoryDocPO(String id, DocType docType, String userId, Date time, String docId, String comment, HistoryDocType state) {
        super(id, docType, userId, time);
        this.docId = docId;
        this.comment = comment;
        this.state = state;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
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
