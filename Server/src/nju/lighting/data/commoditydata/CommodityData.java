package nju.lighting.data.commoditydata;

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

    public CommodityData() {

    }

    @Override
    public List<CommodityItemPO> getAllCommodity() throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        List<CommodityItemPO> commodityItemPOs = null;
        try {
            session.getTransaction().begin();
            String sql = "select com from " + CommodityItemPO.class.getName()
                    + " com ";
            Query<CommodityItemPO> query = session.createQuery(sql);
            commodityItemPOs = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return commodityItemPOs;
    }

    @Override
    public List<CommodityCategoryPO> getAllCommodityCategory() throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        List<CommodityCategoryPO> categoryPOS = null;
        try {
            session.getTransaction().begin();
            String sql = "select comC from " + CommodityCategoryPO.class.getName() + " comC ";
            Query<CommodityCategoryPO> query = session.createQuery(sql);
            categoryPOS = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return categoryPOS;
    }

    @Override
    public CommodityItemPO findById(String id) throws RemoteException {
        CommodityItemPO commodityItemPO = null;
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "select com from " + CommodityItemPO.class.getName()
                    + " com where com.id=:id";
            Query<CommodityItemPO> query = session.createQuery(sql);
            query.setParameter("id", id);
            commodityItemPO = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return commodityItemPO;
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
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.persist(commodityItemPO);
            session.flush();
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

    @Override
    public ResultMessage update(CommodityItemPO commodityItemPO) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.update(commodityItemPO);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();;
            return ResultMessage.FAILURE;
        } finally {
            HibernateUtils.closeSession();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage deleteCommodity(String id) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "delete " + CommodityItemPO.class.getName()
                    + " com where com.id=:id ";;
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

    @Override
    public ResultMessage add(CommodityCategoryPO commodityCategoryPO) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.persist(commodityCategoryPO);
            session.flush();
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
