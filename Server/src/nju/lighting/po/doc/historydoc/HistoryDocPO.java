package nju.lighting.po.doc.historydoc;

import shared.HistoryDocType;

public class HistoryDocPO {

    private String docId;

    private String comment;

    private HistoryDocType state;

    public HistoryDocPO(String docId, String comment, HistoryDocType state) {
        this.docId = docId;
        this.comment = comment;
        this.state = state;
    }
}
