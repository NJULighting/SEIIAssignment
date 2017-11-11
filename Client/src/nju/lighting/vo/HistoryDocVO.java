package nju.lighting.vo;

public class HistoryDocVO {

    private DocVO doc;

    private boolean hasBeenChecked;

    private boolean hasBeenApproved;

    private String approvalOpinion;

    public HistoryDocVO(DocVO doc, boolean hasBeenChecked, boolean hasBeenApproved, String approvalOpinion) {
        this.doc = doc;
        this.hasBeenChecked = hasBeenChecked;
        this.hasBeenApproved = hasBeenApproved;
        this.approvalOpinion = approvalOpinion;
    }

    public DocVO getDoc() {
        return doc;
    }

    public void setDoc(DocVO doc) {
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
}
