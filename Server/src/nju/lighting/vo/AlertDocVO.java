package nju.lighting.vo;

import java.util.ArrayList;

public class AlertDocVO extends DocVO{

    private ArrayList<AlertDocItemVO> alertDocItems;

    private String reason;

    public ArrayList<AlertDocItemVO> getAlertDocItems() {
        return alertDocItems;
    }

    public void setAlertDocItems(ArrayList<AlertDocItemVO> alertDocItems) {
        this.alertDocItems = alertDocItems;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AlertDocVO(ArrayList<AlertDocItemVO> alertDocItems, String reason) {
        this.alertDocItems = alertDocItems;
        this.reason = reason;
    }
}



