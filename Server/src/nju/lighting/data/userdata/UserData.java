package nju.lighting.data.userdata;

import nju.lighting.data.utils.HibernateUtils;
import nju.lighting.dataservice.userdataservice.UserDataService;
import nju.lighting.po.UserPO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import shared.LoginReturnState;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: User的数据层模块
 *
 * @author iznauy
 */
public class UserData implements UserDataService {

    /**
     * 无参构造器
     */
    public UserData() {}


    @Override
    public ResultMessage insert(UserPO po) throws RemoteException {
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
    public UserPO get(String id) throws RemoteException {
        UserPO userPO = null;
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "select user from " + UserPO.class.getName()
                    + " user where user.id=:id";
            Query<UserPO> query = session.createQuery(sql);
            query.setParameter("id", id);
            userPO = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return userPO;
    }

    @Override
    public ResultMessage update(UserPO po) throws RemoteException {
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
    public ResultMessage delete(String id) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "delete " + UserPO.class.getName()
                    + " user where user.id=:id ";;
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
    public List<UserPO> getAll() throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        List<UserPO> userPOs = null;
        try {
            session.getTransaction().begin();
            String sql = "select user from " + UserPO.class.getName()
                    + " user ";
            Query<UserPO> query = session.createQuery(sql);
            userPOs = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return userPOs;
    }

    @Override
    public TwoTuple<UserPO, LoginReturnState> login(String id, String password) throws RemoteException {
        UserPO userPO = null;
        LoginReturnState loginReturnState = LoginReturnState.SUCCESS;
        try {
            userPO = get(id);
            if (userPO == null) {
                userPO = null;
                loginReturnState = LoginReturnState.INVALID_USER_NAME;
            } else if (userPO != null && !userPO.getPassword().equals(password)) {
                userPO = null;
                loginReturnState = LoginReturnState.INVALID_PASSWORD;
            }
        } catch (Exception e) {
            e.printStackTrace();
            loginReturnState = LoginReturnState.UNKNOWN;
        }
        return new TwoTuple<>(userPO, loginReturnState);
    }

}
