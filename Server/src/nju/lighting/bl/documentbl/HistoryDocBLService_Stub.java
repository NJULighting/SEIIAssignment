package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.HistoryDocBLService;
import nju.lighting.vo.HistoryDocVO;
import nju.lighting.vo.LossAndGainDocVO;
import nju.lighting.vo.LossAndGainItemVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class HistoryDocBLService_Stub implements HistoryDocBLService {

    @Override
    public ArrayList<HistoryDocVO> getHistoryDocs(String useid, int startCount, int endCount) throws RemoteException {
        LossAndGainItemVO lossAndGainItemVO1 = new LossAndGainItemVO("xx002001", 100, LossAndGainItemVO.LOSS);
        LossAndGainItemVO lossAndGainItemVO2 = new LossAndGainItemVO("xx002002", 1000, LossAndGainItemVO.LOSS);
        ArrayList<LossAndGainItemVO> itemVOS = new ArrayList<>();
        itemVOS.add(lossAndGainItemVO1);
        itemVOS.add(lossAndGainItemVO2);
        LossAndGainDocVO lossAndGainDocVO = new LossAndGainDocVO(itemVOS, "不小心压坏了！");
        ArrayList<HistoryDocVO> historyDocVOS = new ArrayList<>();
        HistoryDocVO historyDocVO1 = new HistoryDocVO(lossAndGainDocVO, false, false, "");
        historyDocVOS.add(historyDocVO1);
        return historyDocVOS;
    }
}
