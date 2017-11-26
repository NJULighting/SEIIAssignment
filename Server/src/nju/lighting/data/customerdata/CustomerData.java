package nju.lighting.data.customerdata;

import nju.lighting.data.utils.HibernateUtils;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.customer.CustomerPO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 客户模块数据层
 *
 * @author iznauy
 */
public class CustomerData implements CustomerDataService {

    @Override
    public List<CustomerPO> getAllCustomer() throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        List<CustomerPO> customerPOS = null;
        try {
            session.getTransaction().begin();
            String sql = "select cus from " + CustomerPO.class.getName()
                    + " cus ";
            Query<CustomerPO> query = session.createQuery(sql);
            customerPOS = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return customerPOS;
    }

    @Override
    public ResultMessage insertCustomer(CustomerPO po) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.persist(po);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return ResultMessage.FAILURE;
        } finally {
            HibernateUtils.closeSession();
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage deleteCustomer(String id) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "delete " + CustomerPO.class.getName()
                    + " cus where cus.id=:id ";;
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
    public ResultMessage updateCustomer(CustomerPO po) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.update(po);
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
    public CustomerPO getCustomerById(String id) throws RemoteException {
        CustomerPO customerPO = null;
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "select account from " + CustomerPO.class.getName()
                    + " account where account.id=:id ";
            Query<CustomerPO> query = session.createQuery(sql);
            query.setParameter("id", id);
            customerPO = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return customerPO;
    }

    @Override
    public ResultMessage changeReceivable(String customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        customerPO.setReceivable(amount);
        return updateCustomer(customerPO);
    }

    @Override
    public ResultMessage changeReceivableLimit(String customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        customerPO.setReceivableLimit(amount);
        return updateCustomer(customerPO);
    }

    @Override
    public ResultMessage changePayable(String customerId, double amount) throws RemoteException {
        CustomerPO customerPO = getCustomerById(customerId);
        customerPO.setPayable(amount);
        return updateCustomer(customerPO);
    }
}
