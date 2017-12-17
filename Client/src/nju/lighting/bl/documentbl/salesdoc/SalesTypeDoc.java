package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.vo.DocVO;
import nju.lighting.po.doc.DocPO;
import shared.DocType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/11.
 * Description:
 * 销售类单据，是销售单和销售退货单的父类
 * @author 高梦婷
 */
public class SalesTypeDoc extends Doc {

    private String salesTypeDocID;
    private int customerId;
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


    public SalesTypeDoc(String id, DocType docType, String userId, Date time, String salesTypeDocID,
                        int customerId, String salesman, String repository, String remarks,
                        double discount, double voucher, double finalAmount) {
        super(id, docType, userId, time);
        this.salesTypeDocID = salesTypeDocID;
        this.customerId = customerId;
        this.salesman = salesman;
        this.repository = repository;
        this.remarks = remarks;
        this.discount = discount;
        this.voucher = voucher;
        this.finalAmount = finalAmount;
    }

    /**
     * 在商品列表里面增加一个商品项
     * @param salesDocItem
     */
    public void addCommodityListItem(SalesDocItem salesDocItem){
        beforeDiscountAmount = beforeDiscountAmount+salesDocItem.getTotalAmount();
        commodityList.add(salesDocItem);
        commodityListNumber++;
        updateFinalAmount();
    }

    /**
     * 根据商品项的索引删除商品项，第一项的索引为0
     * @param index
     */
    public void deleteCommodityListItem(int index){
        if(commodityListNumber!=0){
            beforeDiscountAmount = beforeDiscountAmount-commodityList.get(index).getTotalAmount();
            commodityList.remove(index);
            commodityListNumber--;
            updateFinalAmount();
        }
    }

    /**
     * 根据索引值得到商品列表里的一个商品项，第一项的索引为0
     * @param index
     * @return SalesDocItem
     */
    public SalesDocItem getCommodityListItem(int index){
        return commodityList.get(index);
    }

    /**
     * 返回目前商品项的个数
     * @return commodityListNumber
     */
    public int getCommodityListNumber(){return commodityListNumber;}

    public String getSalesTypeDocID(){return salesTypeDocID;}

    public void setSalesTypeDocID(String salesTypeDocID) {
        this.salesTypeDocID = salesTypeDocID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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
     * 审批单据，具体方法由其子类实现
     */
    public void approve(){
    }

    @Override
    public ResultMessage reject() {
        return null;
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    /**
     * 由其子类创建相应的VO对象
     * @return 对应的<code>DocVO</code>
     */
    public DocVO toVO(){
        return null;
    }

    /**
     * 由其子类创建响应的PO对象
     * @return 对应的<code>DocPO</code>
     */
    public DocPO toPO(){
        return null;
    }

    @Override
    protected void assignWithPO(DocPO po) {

    }
}
