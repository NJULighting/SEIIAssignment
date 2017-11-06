package nju.lighting.po.doc;

public abstract class DocPO {

    private String id;

    private DocType docType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public DocPO(String id, DocType docType) {
        this.id = id;
        this.docType = docType;
    }
}
