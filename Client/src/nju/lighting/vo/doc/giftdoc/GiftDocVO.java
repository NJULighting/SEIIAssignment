package nju.lighting.vo.doc.giftdoc;


import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.giftdoc.GiftDocPO;
import nju.lighting.po.doc.giftdoc.GiftItemPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public class GiftDocVO extends DocVO {
    private List<GiftItemVO> gifts;
    private int customerID;
    private double total;
    private int promotionID;

    /**
     * Constructor for promotion
     */
    public GiftDocVO(Date time, String creatorId, List<GiftItemVO> gifts, int customerID) {
        super(time, DocType.GIFT, creatorId);
        this.gifts = gifts;
        this.customerID = customerID;

        total = gifts.stream().mapToDouble(GiftItemVO::getSubtotal).sum();
    }


    public GiftDocVO(Date time, String creatorId, List<GiftItemVO> gifts, int customerID, int promotionID) {
        this(time, creatorId, gifts, customerID);
        this.promotionID=promotionID;
        total = gifts.stream().mapToDouble(GiftItemVO::getSubtotal).sum();
    }

    /**
     * Constructor for bl
     */
    public GiftDocVO(Date time, String creatorId, String docId,
                     List<GiftItemVO> gifts, int customerID, int promotionID) {
        super(time, creatorId, docId, DocType.GIFT);
        this.customerID = customerID;
        this.gifts = gifts;
        this.promotionID = promotionID;

        total = gifts.stream().mapToDouble(GiftItemVO::getSubtotal).sum();
    }

    public List<GiftItemVO> getGifts() {
        return gifts;
    }

    public double getTotal() {
        return total;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public String getRepository() {
        return "01";
    }

    @Override
    public DocPO toPO() {
        List<GiftItemPO> poList = CollectionTransformer.toList(gifts, GiftItemVO::toPO);
        return new GiftDocPO(getType(), getCreatorId(), getTime(), poList, customerID, total, promotionID);
    }
}
