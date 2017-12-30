package nju.lighting.data.repositorydata;

import nju.lighting.data.commoditydata.CommodityData;
import nju.lighting.data.commoditydata.CommodityService;
import nju.lighting.data.utils.CommonOperation;
import nju.lighting.data.utils.HibernateUtils;
import nju.lighting.dataservice.repositorydataservice.RepositoryDataService;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.po.repository.RepositoryChangePO;
import nju.lighting.po.repository.RepositoryTableItemPO;
import nju.lighting.po.repository.RepositoryTablePO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import shared.ResultMessage;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 库存模块数据层实现
 * 11.26完成，27日重构
 * @author iznauy
 */
public class RepositoryData extends UnicastRemoteObject implements RepositoryDataService {

    private static final long serialVersionUID = 2958007402413291382L;

    private CommonOperation<RepositoryChangePO> changePOCommonOperation;

    /**
     * 无参数构造器
     */
    public RepositoryData() throws RemoteException {
        this.changePOCommonOperation = new CommonOperation<>(RepositoryChangePO.class.getName());
    }


    @Override
    public List<RepositoryChangePO> getRepositoryChanges(Date startDate, Date endDate) throws RemoteException {
        return changePOCommonOperation.getDataBetweenTime(startDate, endDate, "date");
    }


    @Override
    public RepositoryTablePO getRepositoryTable() throws RemoteException {
        RepositoryTablePO tablePO = new RepositoryTablePO();
        CommodityService commodityData = new CommodityData();
        List<CommodityItemPO> commodityItemPOList = commodityData.getAllCommodity();
        for (CommodityItemPO commodityItemPO: commodityItemPOList) {
            tablePO.addRepositoryItem(new RepositoryTableItemPO(commodityItemPO));
        }
        return tablePO;
    }


    @Override
    public ResultMessage changeRepository(RepositoryChangePO repositoryChangePO) throws RemoteException {
        return changePOCommonOperation.add(repositoryChangePO);
    }

    @Override
    public byte[] exportExcel() throws RemoteException {
        RepositoryTablePO repositoryTablePO = getRepositoryTable();
        List<RepositoryTableItemPO> items = repositoryTablePO.getRepositoryTableItemPOS();
        String head = "库存盘点";
        int headSize = 4;
        File file = new File("");
        return null;
    }
}
