package nju.lighting.vo;

import nju.lighting.po.doc.DocPO;
import shared.DocType;

import java.util.Date;

public abstract class DocVO {

    private Date time;
    private String creatorId;
    private String docId;
    private DocType type;

    /**
     * Constructor for bl
     */
    public DocVO(Date time, String creatorId, String docId, DocType type) {
        this.time = time;
        this.creatorId = creatorId;
        this.docId = docId;
        this.type = type;
    }

    /**
     * Constructor for pre
     */
    public DocVO(Date time, DocType type, String creatorId) {
        this.time = time;
        this.type = type;
        this.creatorId = creatorId;
    }

    /**
     * Transform to po for committing
     * @return corresponding persistent object
     */
    public abstract DocPO toPO();

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getDocId() {
        return docId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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
