package nju.lighting.po;

import java.util.ArrayList;

public class LossAndGainDocPO extends DocPO {

    private ArrayList<LossAndGainItemPO> lossAndGains;

    private String reason;

    public ArrayList<LossAndGainItemPO> getLossAndGains() {
        return lossAndGains;
    }

    public void setLossAndGains(ArrayList<LossAndGainItemPO> lossAndGains) {
        this.lossAndGains = lossAndGains;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LossAndGainDocPO(ArrayList<LossAndGainItemPO> lossAndGains, String reason) {
        this.lossAndGains = lossAndGains;
        this.reason = reason;
    }
}
