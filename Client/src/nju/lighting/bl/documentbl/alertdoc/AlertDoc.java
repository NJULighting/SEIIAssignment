package nju.lighting.bl.documentbl.alertdoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import shared.DocType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/7.
 * Description: 处理库存报警单相关业务
 * @author iznauy
 */
public class AlertDoc extends Doc {

    private ArrayList<AlertDocItem> items;

    public ArrayList<AlertDocItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<AlertDocItem> items) {
        this.items = items;
    }

    public AlertDoc(String id, DocType docType, String userId, Date time, ArrayList<AlertDocItem> items) {
        super(id, docType, userId, time);
        this.items = items;
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
    protected void assignWithPO(DocPO po) {

    }
}
