package shared;

import java.util.Date;

public class DocumentFilter {

    private DocType docType = null;

    private Date startData = null;

    private Date endDate = null;

    private String creatorId = null;

    private String docId = null;

    public DocumentFilter(DocType docType, Date startData, Date endDate, String creatorId, String docId) {
        this.docType = docType;
        this.startData = startData;
        this.endDate = endDate;
        this.creatorId = creatorId;
        this.docId = docId;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public Date getStartData() {
        return startData;
    }

    public void setStartData(Date startData) {
        this.startData = startData;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
}
