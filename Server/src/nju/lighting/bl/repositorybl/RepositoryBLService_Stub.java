package nju.lighting.bl.repositorybl;

import nju.lighting.blservice.repositoryblservice.RepositoryBLService;
import nju.lighting.vo.CommodityVO;
import nju.lighting.vo.RepositoryChangeVO;
import nju.lighting.vo.RepositoryTableItemVO;
import nju.lighting.vo.RepositoryTableVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RepositoryBLService_Stub implements RepositoryBLService {

    @Override
    public ArrayList<RepositoryChangeVO> getRepositoryChanges(long startTime, long endTime) throws RemoteException {
        RepositoryChangeVO changeVO1 = new RepositoryChangeVO(RepositoryChangeVO.BUY, 1000, "xxx0002221", 100000,  startTime);
        RepositoryChangeVO changeVO2 = new RepositoryChangeVO(RepositoryChangeVO.BUY, 1000, "xxx0002222", 10000000,  startTime);
        ArrayList<RepositoryChangeVO> changes = new ArrayList<>();
        changes.add(changeVO1);
        changes.add(changeVO2);
        return changes;
    }

    @Override
    public RepositoryTableVO getRepositoryTable() throws RemoteException {
        CommodityVO commodityVO1 = new CommodityVO("日本LED无障碍灯泡", "LED", "xx0002222",
                100, 100, new ArrayList<Double>(), 150.0, new ArrayList<Double>());
        CommodityVO commodityVO2 = new CommodityVO("日本LED无障碍灯泡" + "-b", "LED", "xx0002223",
                100, 100, new ArrayList<Double>(), 170.0, new ArrayList<Double>());
        RepositoryTableItemVO itemVO1 = new RepositoryTableItemVO(commodityVO1);
        RepositoryTableItemVO itemVO2 = new RepositoryTableItemVO(commodityVO2);
        ArrayList<RepositoryTableItemVO> table = new ArrayList<>();
        table.add(itemVO1);
        table.add(itemVO2);
        return new RepositoryTableVO(table);
    }
}
