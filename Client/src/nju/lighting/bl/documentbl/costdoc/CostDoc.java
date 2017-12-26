package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.accountbl.AccountInfo;
import nju.lighting.bl.accountbl.AccountInfoImpl;
import nju.lighting.bl.accountbl.MockAccountInfo;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.costdoc.CostDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.costdoc.CostDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

/**
 * Created on 2017/11/7.
 * Description: 负责创建初始的现金费用单VO
 * @author Liao
 */
public class CostDoc extends Doc {

    private CostDocItemList itemList = new CostDocItemList();
    private String accountId;
    private double totalAmount;

    public CostDoc(DocVO vo) {
        super(vo);
        assign(vo);
    }

    public CostDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign(historyDocVO.getDocVO());
    }

    public CostDoc(DocPO po) {
        super(po);
        CostDocPO costDocPO = (CostDocPO) po;
        accountId = costDocPO.getAccountID();
        totalAmount = costDocPO.getTotal();

        costDocPO.getItemList().forEach(itemList::add);
    }

    private void assign(DocVO vo) {
        CostDocVO costDocVO = (CostDocVO) vo;
        totalAmount = costDocVO.getTotal();
        accountId = costDocVO.getAccount().getId();

        costDocVO.getItemList().forEach(itemList::add);
    }

    @Override
    public void approve() {
        AccountInfo accountInfo = new MockAccountInfo();
        accountInfo.updateAmount(accountId, totalAmount);
    }

    @Override
    public void redFlush() {
        super.redFlush();
        totalAmount = -totalAmount;
        itemList.redFlush();
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        AccountInfo accountInfo = new AccountInfoImpl();
        return new CostDocVO(createTime, userId, id, accountInfo.getAccountByID(accountId), itemList.toVO());
    }

    @Override
    public DocPO toPO() {
        return new CostDocPO(id, docType, userId, createTime, checkTime,
                approvalComment, state, approvalId, accountId, itemList.toPo(id), totalAmount);
    }

    @Override
    public boolean containsCustomer(String customerId) {
        return false;
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return false;
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
