package nju.lighting.po.doc.historydoc;


import nju.lighting.po.doc.DocType;

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
