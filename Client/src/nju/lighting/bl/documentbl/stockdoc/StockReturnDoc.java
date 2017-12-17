package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.MockCommodity;
import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerInfoImpl;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.stockdoc.StockReturnDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.stockdoc.StockReturnDocVO;
import shared.DocType;

import java.util.Date;

/**
 * Create on 2017/11/12
 * Description:
 * 进货退货单
 * @author 高梦婷
 */
public class StockReturnDoc extends StockTypeDoc {
    public StockReturnDoc(String id, String userId, Date time, String stockTypeDocID,
                          String customerId, String repository, String remarks) {
        super(id, DocType.STOCK_RETURN, userId, time, stockTypeDocID, customerId, repository, remarks);
    }

    /**
     * 审批单据，减少客户应收
     */
    public void approve() {
        CustomerInfo info = new CustomerInfoImpl();
        info.changeReceivable(Integer.parseInt(getCustomerId()), 0 - this.getTotalAmount());
        //减少商品数量
        CommodityInfo commodityInfo = new MockCommodity();
        int listNum = this.getCommodityListNumber();
        for (int i = 0; i < listNum; i++) {
            commodityInfo.subCommodityItem(this.getCommodityListItem(i).getCommodityID(), this.getCommodityListItem(i).getNumber());
        }
    }

    /**
     * 由其子类创建相应的VO对象
     * @return 对应的StockReturnDocVO
     */
    public DocVO toVO() {
        return new StockReturnDocVO(createTime, userId, id, docType, this.getStockTypeDocID(), this.getCustomerId(), this.getRepository(),
                this.getRemarks(), this.getTotalAmount());
    }

    /**
     * 由其子类创建响应的PO对象
     * @return 对应的StockReturnDocPO
     */
    public DocPO toPO() {
        return new StockReturnDocPO(docType, userId, createTime,getCustomerId(), getRepository(), getRemarks(), getTotalAmount(), null);
    }
}
