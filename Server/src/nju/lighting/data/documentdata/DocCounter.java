package nju.lighting.data.documentdata;

import nju.lighting.data.utils.HibernateUtils;
import org.hibernate.Session;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class DocCounter {

    private String className;

    public DocCounter(String className) {
        this.className = className;
    }

    public int countTodayDoc() {
        Session session = HibernateUtils.getCurrentSession();
        int count = -1;
        try {
            session.getTransaction().begin();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtils.closeSession();
        }
        return count;
    }

}
