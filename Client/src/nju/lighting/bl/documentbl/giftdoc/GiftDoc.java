package nju.lighting.bl.documentbl.giftdoc;

import nju.lighting.bl.customerbl.Customer;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.giftdoc.GiftDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.giftdoc.GiftDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/14.
 * Description
 * @author 陈俊宇
 */
public class GiftDoc extends Doc {

    private GiftDocItemList itemList = new GiftDocItemList();
    private int customerID;
    private String repositoryID = "01";
    private double total;
    private int promotionId;

    public GiftDoc(DocVO vo) {
        super(vo);
        assign(vo);
    }

    public GiftDoc(HistoryDocVO historyDocVO) {
        super(historyDocVO);
        assign(historyDocVO.getDocVO());
    }

    public GiftDoc(DocPO po) {
        super(po);
        GiftDocPO giftDocPO = (GiftDocPO) po;
        customerID = giftDocPO.getCustomerID();
        repositoryID = giftDocPO.getRepositoryID();
        total = giftDocPO.getTotal();
        promotionId = giftDocPO.getPromotionId();

        giftDocPO.getGiftItemPOs().forEach(itemList::add);
    }

    @Deprecated
    GiftDoc(String docId, DocType docType, String userId, Date time, ArrayList<GiftDocItem> items, Customer customer) {
        super(docId, docType, userId, time);
        this.docType = DocType.GIFT;
    }

    private void assign(DocVO vo) {
        GiftDocVO giftDocVO = (GiftDocVO) vo;

        customerID = giftDocVO.getCustomerID();
        total = giftDocVO.getTotal();
        promotionId = giftDocVO.getPromotionID();

        giftDocVO.getGifts().forEach(itemList::add);
    }

    public double getTotal() {
        return total;
    }

    @Override
    public void approve() {

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
        return new GiftDocVO(createTime, userId, id, itemList.toVO(), customerID, promotionId);
    }

    @Override
    public DocPO toPO() {
        return new GiftDocPO(id, docType, userId, createTime, checkTime, approvalComment,
                state, approvalId, itemList.toPO(id), customerID, total, promotionId);
    }

    @Override
    public boolean containsCustomer(String customerId) {
        return this.customerID == Integer.parseInt(customerId);
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return itemList.containsCommodity(commodityName);
    }

    @Override
    public boolean containsRepository(String repository) {
        return repositoryID.equals(repository);
    }

    @Override
    public String getCustomer() {
        return Integer.toString(customerID);
    }

    @Override
    public String getRepository() {
        return repositoryID;
    }
}
