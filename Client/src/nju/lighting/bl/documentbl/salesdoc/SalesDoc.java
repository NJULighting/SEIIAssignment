package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerManage;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.salesdoc.SalesDocVO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Create on 2017/11/12
 * Description:
 * 销售单
 * @author 高梦婷
 */
public class SalesDoc extends SalesTypeDoc{
    public SalesDoc(String id, String userId, Date time, String salesTypeDocID,
                    String customerId, String salesman, String repository, String remarks,
                    double discount, double voucher, double finalAmount) {
        super(id, DocType.SALES,userId, time, salesTypeDocID, customerId, salesman,
                repository, remarks, discount, voucher, finalAmount);
    }

    /**
     * 审批单据，增加客户应付
     */
    public void approve(){
        CustomerInfo info = new CustomerManage();
        info.changePayable(this.getCustomerId(),this.getFinalAmount());
    }

    /**
     * 创建相应的VO对象
     * @return SalesDocVO
     */
    public DocVO createVO(){
        return new SalesDocVO(time,userId,id,docType,this.getSalesTypeDocID(),this.getCustomerId()
                ,this.getSalesman(),this.getRepository(),this.getRemarks(),this.getBeforeDiscountAmount()
                ,this.getDiscount(),this.getVoucher(),this.getFinalAmount());
    }

    /**
     * 创建相应的PO对象
     * @return SalesDocPO
     */
    public DocPO createPO(){
        return new SalesDocPO(id,docType,userId,time,this.getSalesTypeDocID(),this.getCustomerId()
                ,this.getRepository(),this.getRemarks()
                ,this.getDiscount(),this.getVoucher(),this.getFinalAmount());
    }
}