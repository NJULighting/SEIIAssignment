package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.LossAndGainDocBLService;
import nju.lighting.po.LossAndGainDocPO;
import nju.lighting.po.ResultMessage;

import java.rmi.RemoteException;

public class LossAndGainDocBLService_Stub implements LossAndGainDocBLService {

    @Override
    public ResultMessage addLossAndGainDoc(LossAndGainDocPO lossAndGainDocPO) throws RemoteException {
        return ResultMessage.SUCCESS;
    }
}
