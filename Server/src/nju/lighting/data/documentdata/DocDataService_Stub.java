package nju.lighting.data.documentdata;

import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.DocPO;
import nju.lighting.po.LossAndGainDocPO;
import nju.lighting.po.LossAndGainItemPO;
import nju.lighting.po.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class DocDataService_Stub implements DocDataService {

    @Override
    public ResultMessage insert(DocPO doc) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<DocPO> findByUser(String user) throws RemoteException {
        LossAndGainItemPO lossAndGainItemVO1 = new LossAndGainItemPO("xx002001", 100, LossAndGainItemPO.LOSS);
        LossAndGainItemPO lossAndGainItemVO2 = new LossAndGainItemPO("xx002002", 1000, LossAndGainItemPO.LOSS);
        ArrayList<LossAndGainItemPO> itemVOS = new ArrayList<>();
        itemVOS.add(lossAndGainItemVO1);
        itemVOS.add(lossAndGainItemVO2);
        LossAndGainDocPO lossAndGainDocPO = new LossAndGainDocPO(itemVOS, "不小心压坏了！");
        ArrayList<DocPO> docs = new ArrayList<>();
        docs.add(lossAndGainDocPO);
        return docs;
    }

    @Override
    public DocPO find(String id) throws RemoteException {
        LossAndGainItemPO lossAndGainItemVO1 = new LossAndGainItemPO("xx002001", 100, LossAndGainItemPO.LOSS);
        LossAndGainItemPO lossAndGainItemVO2 = new LossAndGainItemPO("xx002002", 1000, LossAndGainItemPO.LOSS);
        ArrayList<LossAndGainItemPO> itemVOS = new ArrayList<>();
        itemVOS.add(lossAndGainItemVO1);
        itemVOS.add(lossAndGainItemVO2);
        LossAndGainDocPO lossAndGainDocPO = new LossAndGainDocPO(itemVOS, "不小心压坏了！");
        return lossAndGainDocPO;
    }

    @Override
    public ResultMessage update(DocPO doc) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<DocPO> findByTime(long start, long end) throws RemoteException {
        LossAndGainItemPO lossAndGainItemVO1 = new LossAndGainItemPO("xx002071", 100, LossAndGainItemPO.LOSS);
        LossAndGainItemPO lossAndGainItemVO2 = new LossAndGainItemPO("xx002072", 1000, LossAndGainItemPO.LOSS);
        ArrayList<LossAndGainItemPO> itemVOS = new ArrayList<>();
        itemVOS.add(lossAndGainItemVO1);
        itemVOS.add(lossAndGainItemVO2);
        LossAndGainDocPO lossAndGainDocPO = new LossAndGainDocPO(itemVOS, "不小心压坏了！");
        ArrayList<DocPO> docs = new ArrayList<>();
        docs.add(lossAndGainDocPO);
        return docs;
    }
}
