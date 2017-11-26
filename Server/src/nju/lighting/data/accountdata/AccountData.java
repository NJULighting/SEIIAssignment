package nju.lighting.data.accountdata;

import nju.lighting.data.utils.HibernateUtils;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.po.account.AccountLogPO;
import nju.lighting.po.account.AccountPO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created on 2017/11/26.
 * Description: 账户模块数据层实现
 * @author iznauy
 */
public class AccountData implements AccountDataService {

    /**
     * 无参构造器
     */
    public AccountData() {

    }

    /**
     * 插入一个新账户
     * @param po
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultMessage insert(AccountPO po) throws RemoteException {
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

    /**
     * 根据ID查询账户
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public AccountPO get(String id) throws RemoteException {
        AccountPO accountPO = null;
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "select account from " + AccountPO.class.getName()
                    + " account where account.id=:id ";
            Query<AccountPO> query = session.createQuery(sql);
            query.setParameter("id", id);
            accountPO = query.getSingleResult();
            accountPO.setChangeLogs(getAccountLog(session, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return accountPO;
    }

    /**
     * 获取某个账户的交易记录
     * @param session
     * @param id
     * @return
     */
    private List<AccountLogPO> getAccountLog(Session session, String id) throws Exception {
        String sql = "select accountLogPo from " + AccountLogPO.class.getName()
                + " accountLogPo where accountLogPo.accountID=:id ";
        Query<AccountLogPO> query = session.createQuery(sql);
        query.setParameter("id", id);
        return query.getResultList();
    }

    /**
     * 获取所有账户
     * @return
     * @throws RemoteException
     */
    @Override
    public List<AccountPO> getAll() throws RemoteException {
        List<AccountPO> accountPOS = null;
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "select account from " + AccountPO.class.getName()
                    + " account ";
            Query<AccountPO> query = session.createQuery(sql);
            accountPOS = query.getResultList();
            for (AccountPO accountPO: accountPOS) {
                String id = accountPO.getId();
                accountPO.setChangeLogs(getAccountLog(session, id));
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return accountPOS;
    }

    /**
     * 删除指定id账户
     * @param id
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultMessage delete(String id) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String accountSql = "delete " + AccountPO.class.getName()
                    + " account where account.id=:id ";
            Query accountQuery = session.createQuery(accountSql);
            accountQuery.setParameter("id", id);
            accountQuery.executeUpdate();
            String accountLogSql = "delete " + AccountLogPO.class.getName()
                    + " accountLog where accountLog.accountID=:id";
            Query accountLogQuery = session.createQuery(accountLogSql);
            accountLogQuery.setParameter("id", id);
            accountLogQuery.executeUpdate();
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

    /**
     * 只更新账户信息变更 不更新账户条目信息
     * @param po
     * @return
     * @throws RemoteException
     */
    @Override
    public ResultMessage update(AccountPO po) throws RemoteException {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.update(po);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();;
            return ResultMessage.FAILURE;
        } finally {
            HibernateUtils.closeSession();
        }
        return ResultMessage.SUCCESS;
    }

    /**
     * 增加账户条目
     * @param logPO
     * @return
     */
    @Override
    public ResultMessage add(AccountLogPO logPO) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.persist(logPO);
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
}
