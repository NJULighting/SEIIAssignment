package nju.lighting.bl.documentbl.lossandgaindoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocVO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/11/7.
 * Description: 处理库存报损报溢相关业务
 * @author iznauy
 */
class LossAndGainDoc extends Doc {

    private String comment;
    private LossAndGainDocItemList itemList = new LossAndGainDocItemList();

    LossAndGainDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        LossAndGainDocVO docVO = (LossAndGainDocVO) historyDocVO.getDocVO();
        comment = docVO.getComment();

        docVO.getItems().forEach(itemList::add);
    }

    LossAndGainDoc(DocPO po) {
        super(po);
        comment = ((LossAndGainDocPO) po).getComment();

        ((LossAndGainDocPO) po).getItemPOS().forEach(itemList::add);
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
        return new LossAndGainDocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, comment, itemList.toPO(id));
    }

    @Override
    public boolean containsCustomer(String customerId) {
        return false;
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return itemList.containCommodity(commodityName);
    }

    @Override
    public boolean containsRepository(String repository) {
        return false;
    }

}
