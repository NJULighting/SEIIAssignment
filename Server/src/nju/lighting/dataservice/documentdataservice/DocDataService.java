package nju.lighting.dataservice.documentdataservice;

import nju.lighting.po.doc.DocPO;
import shared.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface DocDataService extends Remote {

    Result<String> commitDoc(DocPO doc) throws RemoteException;

    ResultMessage updateDoc(DocPO doc) throws RemoteException;

    List<DocPO> findByUserId(String id) throws RemoteException;

    List<DocPO> findByState(DocState docState) throws RemoteException;

    List<DocPO> findByType(DocType type) throws RemoteException;

    List<DocPO> findByTime(Date from, Date to) throws RemoteException;

    List<DocPO> findByTimeAndType(Date from, Date to, DocType type) throws RemoteException;

    List<DocPO> findByTypeAndState(DocType type, DocState state) throws RemoteException;

    /**
     * 发送邮件
     * @param creatorId 单据创建者id
     * @param header 邮件标题（要求不能有空格）
     * @param content 邮件内容（要求不能有空格）
     * @throws RemoteException RMI远程调用必备Exception
     */
    void sentMail(String creatorId, String header, String content) throws RemoteException;
}
