package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerManage;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.stockdoc.StockDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.stockdoc.StockDocVO;
import shared.DocType;

import java.util.Date;

/**
 * Create on 2017/11/12
 * Description:
 * 进货单
 * @author 高梦婷
 */
public class StockDoc extends StockTypeDoc{
    public StockDoc(String id, String userId, Date time,
            String stockTypeDocID, String customerId, String repository, String remarks) {
        super(id, DocType.STOCK, userId, time, stockTypeDocID, customerId, repository, remarks);
    }

    /**
     * 审批单据，增加客户应收，增加商品数量
     */
    public void approve(){
        CustomerInfo info = new CustomerManage();
        info.changePayable(this.getCustomerId(),this.getTotalAmount());
    }

    /**
     * 由其子类创建相应的VO对象
     * @return 对应的StockDocVO
     */
    public DocVO createVO(){
        return new StockDocVO(time,userId,id,docType,this.getStockTypeDocID(),this.getCustomerId(),this.getRepository(),
                this.getRemarks(),this.getTotalAmount());
    }

    /**
     * 由其子类创建响应的PO对象
     * @return 对应的StockDocPO
     */
    public DocPO createPO(){
        return new StockDocPO(id,docType,userId,time,this.getStockTypeDocID(),this.getCustomerId(),this.getRepository(),
                this.getRemarks(),this.getTotalAmount());
    }
}