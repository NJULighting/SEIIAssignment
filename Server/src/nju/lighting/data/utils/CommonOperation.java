package nju.lighting.data.utils;

import org.hibernate.Session;
import org.hibernate.query.Query;
import shared.ResultMessage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/27.
 * Description: 常见方法的工具类
 *
 * @author iznauy
 */
public class CommonOperation<T> implements Serializable {

    /**
     * 用于存储类的完全名称，通过T.class.getName方法获取
     */
    private String className;

    public CommonOperation(String className) {
        this.className = className;
    }

    public List<T> getAll() {
        Session session = HibernateUtils.getCurrentSession();
        List<T> jrs = null;
        try {
            session.getTransaction().begin();
            String sql = "select t from " + className + " t ";
            Query<T> query = session.createQuery(sql);
            jrs = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return jrs;
    }

    public <V> T getBySingleField(String fieldName, V target) {
        T t = null;
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "select t from " + className + " t where t." + fieldName + "=:field";
            Query<T> query = session.createQuery(sql);
            query.setParameter("field", target);
            t = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return t;
    }

    public List<T> getDataBetweenTime(Date startDate, Date endDate, String fieldName) {
        Session session = HibernateUtils.getCurrentSession();
        java.sql.Date start = new java.sql.Date(startDate.getTime());
        java.sql.Date end = new java.sql.Date(endDate.getTime());
        List<T> dataPOS = null;
        try {
            session.getTransaction().begin();
            String sql = "select t from " + className + " t where t." + fieldName + " between '"
                    + start.toString() + "' and '" + end.toString() + "' order by t." +fieldName;
            Query<T> query = session.createQuery(sql);
            dataPOS = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            HibernateUtils.closeSession();
        }
        return dataPOS;
    }

    public <V> List<T> getListBySingleField(String fieldName, V target) {
        List<T> ts = null;
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "select t from " + className + " t where t." + fieldName + "=:field";
            Query<T> query = session.createQuery(sql);
            query.setParameter("field", target);
            ts = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return ts;
    }

    public ResultMessage add(T t) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.persist(t);
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

    public <V> ResultMessage deleteBySingleField(String fieldName, V target) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            String sql = "delete from " + className + " t where t." + fieldName + "=:field";
            Query<T> query = session.createQuery(sql);
            query.setParameter("field", target);
            query.executeUpdate();
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

    public ResultMessage addList(List<T> ts) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            for (T t: ts) {
                session.persist(t);
            }
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


    public ResultMessage update(T tpo) {
        Session session = HibernateUtils.getCurrentSession();
        try {
            session.getTransaction().begin();
            session.update(tpo);
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

}
