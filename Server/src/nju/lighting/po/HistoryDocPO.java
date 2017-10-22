package nju.lighting.po;

public class HistoryDocPO {

    private DocPO doc;

    private boolean hasBeenChecked;

    private boolean hasBeenApproved;

    private String approvalOpinion;

    public DocPO getDoc() {
        return doc;
    }

    public void setDoc(DocPO doc) {
        this.doc = doc;
    }

    public boolean isHasBeenChecked() {
        return hasBeenChecked;
    }

    public void setHasBeenChecked(boolean hasBeenChecked) {
        this.hasBeenChecked = hasBeenChecked;
    }

    public boolean isHasBeenApproved() {
        return hasBeenApproved;
    }

    public void setHasBeenApproved(boolean hasBeenApproved) {
        this.hasBeenApproved = hasBeenApproved;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public HistoryDocPO(DocPO doc, boolean hasBeenChecked, boolean hasBeenApproved, String approvalOpinion) {
        this.doc = doc;
        this.hasBeenChecked = hasBeenChecked;
        this.hasBeenApproved = hasBeenApproved;
        this.approvalOpinion = approvalOpinion;
    }
}
