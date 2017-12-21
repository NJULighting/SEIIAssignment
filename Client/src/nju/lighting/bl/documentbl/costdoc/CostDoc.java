package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.accountbl.AccountInfo;
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

    public CostDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        CostDocVO docVO = (CostDocVO) historyDocVO.getDocVO();
        accountId = docVO.getAccount().getId();
        totalAmount = docVO.getTotal();

        docVO.getItemList().forEach(itemList::add);
    }

    public CostDoc(DocPO po) {
        super(po);
        CostDocPO costDocPO = (CostDocPO) po;
        accountId = costDocPO.getAccountID();
        totalAmount = costDocPO.getTotal();

        costDocPO.getItemList().forEach(itemList::add);
    }

    @Override
    public void approve() {
        AccountInfo accountInfo = new MockAccountInfo();
        accountInfo.updateAmount(accountId, totalAmount);
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

}
