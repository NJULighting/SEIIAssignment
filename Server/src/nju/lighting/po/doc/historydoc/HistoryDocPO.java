package nju.lighting.po.doc.historydoc;


import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.DocType;

public class HistoryDocPO extends DocPO{

    private String docId;

    private String comment;

    private HistoryDocType state;

    public HistoryDocPO(String id, DocType docType,
                        String docId, String comment, HistoryDocType state) {
        super(id, docType);
        this.docId = docId;
        this.comment = comment;
        this.state = state;
    }
}
