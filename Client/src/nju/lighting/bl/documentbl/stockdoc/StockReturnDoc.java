package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerManage;
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
public class StockReturnDoc extends StockTypeDoc{
    public StockReturnDoc(String id,String userId, Date time, String stockTypeDocID,
                          String customerId, String repository, String remarks) {
        super(id, DocType.STOCK_RETURN, userId, time, stockTypeDocID, customerId, repository, remarks);
    }
    /**
     * 审批单据，减少客户应收
     */
    public void approve(){
        CustomerInfo info = new CustomerManage();
        info.changePayable(this.getCustomerId(),0-this.getTotalAmount());
    }

    /**
     * 由其子类创建相应的VO对象
     * @return 对应的StockReturnDocVO
     */
    public DocVO createVO(){
        return new StockReturnDocVO(time,userId,id,docType,this.getStockTypeDocID(),this.getCustomerId(),this.getRepository(),
                this.getRemarks(),this.getTotalAmount());
    }

    /**
     * 由其子类创建响应的PO对象
     * @return 对应的StockReturnDocPO
     */
    public DocPO createPO(){
        return new StockReturnDocPO(id,docType,userId,time,this.getStockTypeDocID(),this.getCustomerId(),this.getRepository(),
                this.getRemarks(),this.getTotalAmount());
    }
}