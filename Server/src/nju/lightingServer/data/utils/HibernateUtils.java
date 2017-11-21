package nju.lightingServer.data.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created on 2017/11/20.
 * Description: 用于处理Hibernate框架初始化以及数据库连接的工具类
 * @author iznauy
 */
public class HibernateUtils {

    /**
     * Session工厂类
     */
    private static final SessionFactory sessonFactory;

    /**
     * 为每个线程单独持有session（可以为空）
     */
    private static ThreadLocal<Session> sesson = new ThreadLocal<>();

    /**
     * 初始化SessionFactory
     */
    static {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        sessonFactory = config.buildSessionFactory();
    }

    /**
     * 获取当前线程对应的session，如果没有，则创建
     * @return 当前线程对应的session
     */
    public static Session getCurrentSession() {
        Session ses = sesson.get();
        if (ses == null) {
            ses = sessonFactory.openSession();
            sesson.set(ses);
        }
        return ses;
    }

    /**
     * 关闭当前线程对应的session
     */
    public static void closeSession() {
        Session ses = sesson.get();
        if (ses != null) {
            ses.close();
        }
        sesson.set(null);
    }

}
