package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public void approve() {

    }

    @Override
    public ResultMessage reject() {
        return null;
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        return null;
    }

    @Override
    public DocPO toPO() {
        return null;
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
}
