package nju.lighting.data.repositorydata;

import nju.lighting.data.commoditydata.CommodityData;
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

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 库存模块数据层实现
 * 11.26完成，27日重构
 * @author iznauy
 */
public class RepositoryData implements RepositoryDataService {

    private CommonOperation<RepositoryChangePO> changePOCommonOperation;

    /**
     * 无参数构造器
     */
    public RepositoryData() {
        this.changePOCommonOperation = new CommonOperation<>(RepositoryChangePO.class.getName());
    }

    /**
     * 获取商品变化情况
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @throws RemoteException
     */
    @Override
    public List<RepositoryChangePO> getRepositoryChanges(Date startDate, Date endDate) throws RemoteException {
//        Session session = HibernateUtils.getCurrentSession();
//        java.sql.Date start = new java.sql.Date(startDate.getTime());
//        java.sql.Date end = new java.sql.Date(endDate.getTime());
//        List<RepositoryChangePO> changePOS = null;
//        try {
//            session.getTransaction().begin();
//            String sql = "select change from " + RepositoryChangePO.class.getName() + " change  where change.date between '"
//                    + start.toString() + "' and '" + end.toString() + "' order by change.date";
//            System.out.println(sql);
//            Query<RepositoryChangePO> query = session.createQuery(sql);
//            changePOS = query.getResultList();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.getTransaction().rollback();
//            return null;
//        } finally {
//            HibernateUtils.closeSession();
//        }
//        return changePOS;
        return changePOCommonOperation.getDataBetweenTime(startDate, endDate, "date");
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public RepositoryTablePO getRepositoryTable() throws RemoteException {
        RepositoryTablePO tablePO = new RepositoryTablePO();
        CommodityData commodityData = new CommodityData();
        List<CommodityItemPO> commodityItemPOList = commodityData.getAllCommodity();
        for (CommodityItemPO commodityItemPO: commodityItemPOList) {
            tablePO.addRepositoryItem(new RepositoryTableItemPO(commodityItemPO));
        }
        return tablePO;
    }

    /**
     * 变更库存情况
     * @param repositoryChangePO
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultMessage changeRepository(RepositoryChangePO repositoryChangePO) throws RemoteException {
        return changePOCommonOperation.add(repositoryChangePO);
    }
}
