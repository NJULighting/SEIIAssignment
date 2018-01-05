package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created on 2017/11/7.
 * Description: 处理库存报警单相关业务
 * @author iznauy
 */
public class AlertDoc extends Doc {

    private AlertDocItemList itemList;
    private String comment;
    private boolean triggered;
    private boolean expired;

    public AlertDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        AlertDocVO alertDocVO = (AlertDocVO) historyDocVO.getDocVO();
        comment = alertDocVO.getComment();
        triggered = alertDocVO.isTriggered();
        expired = alertDocVO.isExpired();

        // Item list
        itemList = new AlertDocItemList();
        alertDocVO.getItems().forEach(itemList::add);
    }

    public AlertDoc(DocPO po) {
        super(po);
        AlertDocPO alertDocPO = (AlertDocPO) po;
        comment = alertDocPO.getComment();
        triggered = alertDocPO.isTriggered();
        expired = alertDocPO.isExpired();

        // Item list
        itemList = new AlertDocItemList();
        alertDocPO.getItemPOS().forEach(itemList::add);
    }

    /**
     * This constructor is used for expiring this doc
     */
    public AlertDoc(AlertDocVO docVO) {
        super(docVO);
        comment = docVO.getComment();
        triggered = docVO.isTriggered();
        expired = docVO.isExpired();

        itemList = new AlertDocItemList();
        docVO.getItems().forEach(itemList::add);
    }

    public void triggerAlert(String commodityId, int count) {
        if (!expired) {
            triggered = itemList.triggered(commodityId, count);
        }

        // If the document is triggered, update to the database
        if (triggered) {
            try {
                DocDataService dataService = DataFactory.getDataBase(DocDataService.class);
                dataService.updateDoc(toPO());
            } catch (NamingException | RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultMessage expireAlert() {
        try {
            expired = true;
            DocDataService dataService = DataFactory.getDataBase(DocDataService.class);
            return dataService.updateDoc(toPO());
        } catch (NamingException | RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    @Override
    public ResultMessage approve() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void redFlush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        return new AlertDocVO(createTime, userId, id, itemList.toVO(), comment, triggered, expired);
    }

    @Override
    public DocPO toPO() {
        return new AlertDocPO(id, docType, userId, createTime, checkTime, approvalComment, state, approvalId, comment,
                triggered, expired, itemList.toPO(id));
    }

    @Override
    public boolean containsCustomer(String customerId) {
        return false;
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return itemList.containsCommodity(commodityName);
    }

    @Override
    public boolean containsRepository(String repository) {
        return false;
    }

    @Override
    public String getCustomer() {
        return null;
    }

    @Override
    public String getRepository() {
        return null;
    }
}
