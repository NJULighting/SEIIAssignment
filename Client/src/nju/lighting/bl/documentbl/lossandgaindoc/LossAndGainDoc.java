package nju.lighting.bl.documentbl.lossandgaindoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.RedFlush;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocVO;
import shared.ResultMessage;

/**
 * Created on 2017/11/7.
 * Description: 处理库存报损报溢相关业务
 * @author iznauy
 */
public class LossAndGainDoc extends Doc {

    private String comment;
    private LossAndGainDocItemList itemList = new LossAndGainDocItemList();

    public LossAndGainDoc(DocVO vo) {
        super(vo);
        assign((LossAndGainDocVO) vo);
    }

    public LossAndGainDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign((LossAndGainDocVO) historyDocVO.getDocVO());
    }

    public LossAndGainDoc(DocPO po) {
        super(po);
        comment = ((LossAndGainDocPO) po).getComment();

        ((LossAndGainDocPO) po).getItemPOS().forEach(itemList::add);
    }

    private void assign(DocVO vo) {
        LossAndGainDocVO docVO = (LossAndGainDocVO) vo;
        comment = docVO.getComment();
        docVO.getItems().forEach(itemList::add);
    }

    /**
     * 得到报损（报溢）的支出（收入）
     */
    public double getAmount() {
        return itemList.getAmount();
    }

    @Override
    public void approve() {

    }

    @Override
    public ResultMessage reject() {
        return null;
    }

    @Override
    public void redFlush() {
        comment = RedFlush.RED_FLUSH_COMMENT;
        itemList.redFlush();
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        return new LossAndGainDocVO(createTime, userId, id, docType, itemList.toVO(), comment);
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

    @Override
    public String getCustomer() {
        return null;
    }

    @Override
    public String getRepository() {
        return null;
    }

}
