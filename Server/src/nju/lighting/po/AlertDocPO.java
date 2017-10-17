package nju.lighting.po;

import java.util.ArrayList;

public class AlertDocPO extends DocPO{

    private ArrayList<AlertDocItemPO> alertDocItems;

    private String reason;

    public ArrayList<AlertDocItemPO> getAlertDocItems() {
        return alertDocItems;
    }

    public void setAlertDocItems(ArrayList<AlertDocItemPO> alertDocItems) {
        this.alertDocItems = alertDocItems;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}



