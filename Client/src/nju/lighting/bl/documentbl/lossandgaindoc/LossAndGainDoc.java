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
        assign(vo);
    }

    public LossAndGainDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign(historyDocVO.getDocVO());
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
     * Get revenue(expenditure) of this gain(loss) doc
     */
    public double getAmount() {
        return itemList.getAmount();
    }

    @Override
    public ResultMessage approve() {
        return itemList.approve();
    }

    @Override
    public void redFlush() {
        comment = RedFlush.RED_FLUSH_COMMENT;
        itemList.redFlush();
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
