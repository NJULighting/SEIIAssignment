package nju.lighting.data.documentdata;

import nju.lighting.blservice.commodityblservice.CommodityBLService;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.*;
import nju.lighting.po.init.InitPO;
import nju.lighting.vo.CommodityVO;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class DocDataService_Stub implements DocDataService {

    @Override
    public ResultMessage insert(DocPO doc) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<DocPO> findByUser(String user) {
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
    public DocPO find(String id) {
        LossAndGainItemPO lossAndGainItemVO1 = new LossAndGainItemPO("xx002001", 100, LossAndGainItemPO.LOSS);
        LossAndGainItemPO lossAndGainItemVO2 = new LossAndGainItemPO("xx002002", 1000, LossAndGainItemPO.LOSS);
        ArrayList<LossAndGainItemPO> itemVOS = new ArrayList<>();
        itemVOS.add(lossAndGainItemVO1);
        itemVOS.add(lossAndGainItemVO2);
        LossAndGainDocPO lossAndGainDocPO = new LossAndGainDocPO(itemVOS, "不小心压坏了！");
        return lossAndGainDocPO;
    }

    @Override
    public ResultMessage update(DocPO doc) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<DocPO> findByTime(long start, long end) {
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

    @Override
    public ResultMessage newAccount(InitPO po) {

        return po == null ? ResultMessage.FAILURE : ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<InitPO> getInitInfo() throws RemoteException {
        CommodityBLService commodityBLService = new CommodityBLService_Stub();
        ArrayList<CommodityVO> commodityVOS = commodityBLService.getCommodityList();
        ArrayList<String> commodities = new ArrayList<>();
        ArrayList<String> commotityTypes = new ArrayList<>();
        for (CommodityVO vo : commodityVOS) {
            commodities.add(vo.getName());
            commotityTypes.add(vo.getCommodityType());
        }
        // Customers
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Frog0");
        customers.add("Frog1");
        // Accounts
        ArrayList<String> accounts = new ArrayList<>();
        accounts.add("Account0");
        accounts.add("Account1");
        InitPO initPO = new InitPO(commotityTypes, commodities, customers, accounts);

        ArrayList<InitPO> initPOS = new ArrayList<>();
        initPOS.add(initPO);
        return initPOS;
    }

    @Override
    public void init() {

    }

    @Override
    public void finish() {

    }
}
