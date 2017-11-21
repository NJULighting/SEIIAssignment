package nju.lighting.bl.documentbl.stockdoc;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/11.
 * Description:
 * 进货类单据，是进货单和进货退货单的父类
 * @author 高梦婷
 */
public class StockTypeDoc extends Doc {

    private String stockTypeDocID;
    private String customerId;
    private String repository;
    private String remarks;
    private ArrayList<StockDocItem> commodityList;
    private int commodityListNumber = 0;
    private double totalAmount = 0;


    public StockTypeDoc(String id, DocType docType, String userId, Date time,
                        String stockTypeDocID, String customerId, String repository,
                        String remarks) {
        super(id, docType, userId, time);
        this.stockTypeDocID = stockTypeDocID;
        this.customerId = customerId;
        this.repository = repository;
        this.remarks = remarks;
    }

    /**
     * 在商品列表里面增加一个商品项
     * @param stockDocItem
     */
    public void addCommodityListItem(StockDocItem stockDocItem){
        totalAmount = totalAmount+stockDocItem.getTotalAmount();
        commodityList.add(stockDocItem);
        commodityListNumber++;
    }

    /**
     * 根据商品项的索引删除商品项，第一项的索引为0
     * @param index
     */
    public void deleteCommodityListItem(int index){
        if(commodityListNumber!=0){
            totalAmount = totalAmount-commodityList.get(index).getTotalAmount();
            commodityList.remove(index);
            commodityListNumber--;
        }
    }

    /**
     * 根据索引值得到商品列表里的一个商品项，第一项的索引为0
     * @param index
     * @return StockDocItem
     */
    public StockDocItem getCommodityListItem(int index){return commodityList.get(index);}

    /**
     * 返回目前商品项的个数
     * @return commodityListNumber
     */
    public int getCommodityListNumber(){return commodityListNumber;}

    public String getStockTypeDocID(){return stockTypeDocID;}

    public void setStockTypeDocID(String stockTypeDocID) {
        this.stockTypeDocID = stockTypeDocID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public double getTotalAmount(){return totalAmount;}


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
     * 创建相应的VO对象
     * @return 对应的<code>DocVO</code>
     */
    public DocVO createVO(){
        return null;
    }

    /**
     * 由其子类创建响应的PO对象
     * @return 对应的<code>DocPO</code>
     */
    public DocPO createPO(){
        return null;
    }
}
