package nju.lighting.vo;

import java.util.ArrayList;

public class LossAndGainDocVO extends DocVO {

    private ArrayList<LossAndGainItemVO> lossAndGains;

    private String reason;

    public LossAndGainDocVO(ArrayList<LossAndGainItemVO> lossAndGains, String reason) {
        this.lossAndGains = lossAndGains;
        this.reason = reason;
    }

    public ArrayList<LossAndGainItemVO> getLossAndGains() {
        return lossAndGains;
    }

    public void setLossAndGains(ArrayList<LossAndGainItemVO> lossAndGains) {
        this.lossAndGains = lossAndGains;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
