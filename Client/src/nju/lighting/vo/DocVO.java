package nju.lighting.vo;

import shared.DocType;

import java.util.Date;

public abstract class DocVO {

    private Date time;
    private String creatorId;
    private String docId;
    private DocType type;

    public DocVO(Date time, String creatorId, String docId, DocType type) {
        this.time = time;
        this.creatorId = creatorId;
        this.docId = docId;
        this.type = type;
    }

    public DocVO(Date time, String creatorId, DocType type) {
        this.time = time;
        this.creatorId = creatorId;
        this.type = type;
    }

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
}
