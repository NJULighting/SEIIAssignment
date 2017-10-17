package nju.lighting.po;

public abstract class DocPO {

    private long time;

    private int creatorId;

    private int docId;

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    private String docNumber;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
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
