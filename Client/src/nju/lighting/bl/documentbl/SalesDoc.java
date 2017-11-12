package nju.lighting.bl.documentbl;

import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.SalesDocVO;
import nju.lighting.po.doc.DocPO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/11.
 * Description:
 * @author Gao
 */
public class SalesDoc extends Doc{

    private String salesTypeDocID;
    private String customerId;
    private String salesman;
    private String repository;
    private String remarks;
    private ArrayList<SalesDocItem> commodityList;
    private int commodityListNumber = 0;
    private double beforeDiscountAmount = 0;
    private double discount = 0;
    private double voucher = 0;
    private double finalAmount = 0;

    private void updateFinalAmount(){finalAmount = beforeDiscountAmount - discount - voucher;}


    public SalesDoc(Date time, String userId, String id, String salesTypeDocID, String customerId, String salesman,
                    String repository, String remarks,
                    double beforeDiscountAmount, double discount,
                    double voucher) {
        super(id, DocType.SALES, userId, time);
        this.salesTypeDocID = salesTypeDocID;
        this.customerId = customerId;
        this.salesman = salesman;
        this.repository = repository;
        this.userId = userId;
        this.remarks = remarks;
        this.beforeDiscountAmount = beforeDiscountAmount;
        this.discount = discount;
        this.voucher = voucher;
    }

    public void setId(String id){this.id = id;}
    public String getId(){return id;}
    public void setTime(Date time){this.time = time;}
    public Date getTime(){return time;}
    public void setUserId(String userId){this.userId = userId;}
    public String getUserrId(){return userId;}

    public DocType getDocType(){return docType;}


    /**
     * 在商品列表里面增加一个商品项
     * @param salesDocItem
     */
    public void addCommodityList(SalesDocItem salesDocItem){
        commodityList.add(salesDocItem);
        commodityListNumber++;
    }

    /**
     * 根据商品项的索引删除商品项，第一项的索引为0
     * @param index
     */
    public void deleteCommodityList(int index){
        if(commodityListNumber!=0){
            commodityList.remove(index);
            commodityListNumber--;
        }
    }

    /**
     * 根据索引值得到商品列表里的一个商品项，第一项的索引为0
     * @param index
     * @return SalesDocItem
     */
    public SalesDocItem getCommodityList(int index){
        return commodityList.get(index);
    }


    public int getCommodityListNumber(){return commodityListNumber;}


    public void setSalesTypeDocID(String salesTypeDocID) {
        this.salesTypeDocID = salesTypeDocID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getBeforeDiscountAmount() {
        return beforeDiscountAmount;
    }

    public void setBeforeDiscountAmount(double beforeDiscountAmount) {
        this.beforeDiscountAmount = beforeDiscountAmount;
        updateFinalAmount();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        updateFinalAmount();
    }

    public double getVoucher() {
        return voucher;
    }

    public void setVoucher(double voucher) {
        this.voucher = voucher;
        updateFinalAmount();
    }

    public double getFinalAmount() {
        return finalAmount;
    }


    /**
     * 审批单据
     */
    void approve(){
        
    }

    /**
     * 创建相应的VO对象
     * @return 对应的<code>DocVO</code>
     */
    DocVO createVO(){
        return new SalesDocVO(time,userId,id,docType,salesTypeDocID,customerId
                ,salesman,repository,remarks,beforeDiscountAmount,discount,voucher,finalAmount);
    }

    /**
     * 创建响应的PO对象
     * @return 对应的<code>DocPO</code>
     */
    DocPO createPO(){
        return new SalesDocPO(id,docType,userId,time,salesTypeDocID,customerId,repository,remarks,discount,voucher,finalAmount);
    }
}
