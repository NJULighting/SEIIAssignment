package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.MockCommodity;
import nju.lighting.bl.customerbl.CustomerInfo;
import nju.lighting.bl.customerbl.CustomerInfoImpl;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesReturnDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.salesdoc.SalesReturnDocVO;
import shared.DocType;

import java.util.Date;

/**
 * Create on 2017/11/12
 * Description:
 * 销售退货单
 * @author 高梦婷
 */
public class SalesReturnDoc extends SalesTypeDoc{
    public SalesReturnDoc(String id, String userId, Date time,
                          String salesTypeDocID, int customerId, String salesman,
                          String repository, String remarks, double discount,
                          double voucher, double finalAmount) {
        super(id, DocType.SALES_RETURN, userId, time, salesTypeDocID, customerId, salesman,
                repository, remarks, discount, voucher, finalAmount);
    }

    /**
     * 审批单据，减少客户应付
     */
    public void approve(){
        CustomerInfo info = new CustomerInfoImpl();
        info.changePayable(this.getCustomerId(),0-this.getFinalAmount());
        //增加商品数量
        CommodityInfo commodityInfo = new MockCommodity();
        int listNum = this.getCommodityListNumber();
        for(int i=0;i<listNum;i++){
            commodityInfo.addCommodityItem(this.getCommodityListItem(i).getCommodityID(),this.getCommodityListItem(i).getNumber());
        }
    }


    /**
     * 创建相应的VO对象
     * @return SalesReturnDocVO
     */
    public DocVO toVO(){
        return new SalesReturnDocVO(createTime,userId,id,docType,this.getCustomerId()
                ,this.getSalesman(),this.getRepository(),this.getRemarks(),this.getBeforeDiscountAmount()
                ,this.getDiscount(),this.getVoucher(),this.getFinalAmount());
    }

    /**
     * 创建相应的PO对象
     * @return SalesReturnDocPO
     */
    public DocPO toPO(){
        return null;
    }
}
