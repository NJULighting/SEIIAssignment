package nju.lighting.po;

import java.util.Date;

public abstract class DocPO {

    private Date time;

    private String creatorId;

    private String docId;

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    private String docNumber;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public DocType getType() {
        return type;
    }

    public void setType(DocType type) {
        this.type = type;
    }

    private DocType type;


}
