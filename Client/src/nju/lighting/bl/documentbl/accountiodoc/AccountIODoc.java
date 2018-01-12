package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

import java.util.List;

/**
 * Created on 2017/11/7.
 * Description: 负责创建初始的收付款单
 * @author Liao
 */
abstract class AccountIODoc extends Doc {

    String customerID;
    double total;
    AccountDocItemList itemList;

    /**
     * Constructor for red flushing
     */
    AccountIODoc(DocVO vo) {
        super(vo);
        assign(vo);
    }

    /**
     * Constructor for approval module
     */
    AccountIODoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign(historyDocVO.getDocVO());
    }

    /**
     * Used for searching
     */
    AccountIODoc(DocPO po) {
        super(po);
    }

    /**
     * Assign specific fields. The vo will be casted to <tt>AccountIODocVO</tt>,
     * and the customerID, total will be assigned. <tt>ItemList</tt> need to be
     * assigned in child classes <tt>AccountInDoc</tt> and <tt>AccountOutDoc</tt>
     */
    private void assign(DocVO vo) {
        AccountIODocVO docVO = (AccountIODocVO) vo;
        customerID = docVO.getCustomer();
        total = docVO.getTotal();
    }

    /**
     * Assign the <tt>AccountDocItemList</tt>. <tt>AccountInDoc</tt> will pass
     * <tt>IN</tt> as the second parameter,and <tt>AccountOutDoc</tt> will pass
     * <tt>OUT</tt> as the second parameters
     */
    void assignItemList(DocVO docVO, AccountIOType ioType) {
        itemList = new AccountDocItemList(ioType);
        List<AccountTransferItemVO> voItemList = ((AccountIODocVO) docVO).getTransferAccountList();
        voItemList.forEach(itemList::add);
    }

    @Override
    public void redFlush() {
        total = -total;
        itemList.redFlush();
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
