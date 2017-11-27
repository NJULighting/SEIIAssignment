package nju.lighting.data.commoditydata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.data.utils.HibernateUtils;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 商品模块数据层实现
 * @author iznauy
 */
public class CommodityData implements CommodityDataService {

    private CommonOperation<CommodityItemPO> commodityItemPOCommonOperation;

    private CommonOperation<CommodityCategoryPO> categoryPOCommonOperation;

    public CommodityData() {
        this.commodityItemPOCommonOperation = new CommonOperation<>(CommodityItemPO.class.getName());
        this.categoryPOCommonOperation = new CommonOperation<>(CommodityCategoryPO.class.getName());
    }

    @Override
    public List<CommodityItemPO> getAllCommodity() throws RemoteException {
        return commodityItemPOCommonOperation.getAll();
    }

    @Override
    public List<CommodityCategoryPO> getAllCommodityCategory() throws RemoteException {
        return categoryPOCommonOperation.getAll();
    }

    @Override
    public CommodityItemPO findById(String id) throws RemoteException {
        return commodityItemPOCommonOperation.getBySingleField("id", id);
    }

    @Override
    public List<CommodityItemPO> findByName(String name) throws RemoteException {
        List<CommodityItemPO> itemPOS = null;
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "select com from " + CommodityItemPO.class.getName()
                    + " com where com.name=:name ";
            Query<CommodityItemPO> query = session.createQuery(sql);
            query.setParameter("name", name);
            itemPOS = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return itemPOS;
    }


    @Override
    public ResultMessage add(CommodityItemPO commodityItemPO) throws RemoteException {
        return commodityItemPOCommonOperation.add(commodityItemPO);
    }

    @Override
    public ResultMessage update(CommodityItemPO commodityItemPO) throws RemoteException {
        return commodityItemPOCommonOperation.update(commodityItemPO);
    }

    @Override
    public ResultMessage deleteCommodity(String id) throws RemoteException {
        return commodityItemPOCommonOperation.deleteBySingleField("id", id);
    }

    @Override
    public ResultMessage add(CommodityCategoryPO commodityCategoryPO) throws RemoteException {
        return categoryPOCommonOperation.add(commodityCategoryPO);
    }

    @Override
    public ResultMessage deleteCategory(int id) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "delete " + CommodityCategoryPO.class.getName()
                    + " where id=:id ";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return ResultMessage.FAILURE;
        } finally {
            HibernateUtils.closeSession();
        }
        return ResultMessage.SUCCESS;
    }
}
