package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

/**
 * Created on 2017/11/7.
 * Description: 负责创建初始的收付款单
 * @author Liao
 */
abstract class AccountIODoc extends Doc {

    String customerID;
    double total;
    AccountDocItemList itemList = new AccountDocItemList();

    AccountIODoc(DocVO vo) {
        super(vo);
        assign(vo);
    }

    /**
     * Constructor for approval module
     */
    AccountIODoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);

        // Assign specific fields
        assign(historyDocVO.getDocVO());
    }

    /**
     * Used for searching
     */
    AccountIODoc(DocPO po) {
        super(po);
    }

    private void assign(DocVO vo) {
        AccountIODocVO docVO = (AccountIODocVO) vo;
        customerID = docVO.getCustomer();
        total = docVO.getTotal();
        itemList = new AccountDocItemList();
        docVO.getTransferAccountList().forEach(itemList::add);
    }

    @Override
    public void redFlush() {
        super.redFlush();
        total = -total;
        itemList.redFlush();
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO toVO() {
        return new AccountIODocVO(createTime, userId, id, docType, customerID, itemList.toVO());
    }


    @Override
    public boolean containsCustomer(String customerId) {
        return this.customerID.equals(customerId);
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
        return customerID;
    }

    @Override
    public String getRepository() {
        return null;
    }

}
