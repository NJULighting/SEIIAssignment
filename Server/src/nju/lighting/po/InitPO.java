package nju.lighting.po;

import java.util.ArrayList;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class InitPO {
    private ArrayList<CommodityCategoryPO> commodityCategories;
    private ArrayList<CommodityPO> commodities;
    private ArrayList<CustomerPO> customes;
    private ArrayList<AccountPO> accounts;

    public InitPO(ArrayList<CommodityCategoryPO> commodityCategories, ArrayList<CommodityPO> commodities
            , ArrayList<CustomerPO> customes, ArrayList<AccountPO> accounts) {
        this.commodityCategories = commodityCategories;
        this.commodities = commodities;
        this.customes = customes;
        this.accounts = accounts;
    }

    public ArrayList<CommodityCategoryPO> getCommodityCategories() {
        return commodityCategories;
    }

    public void setCommodityCategories(ArrayList<CommodityCategoryPO> commodityCategories) {
        this.commodityCategories = commodityCategories;
    }

    public ArrayList<CommodityPO> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<CommodityPO> commodities) {
        this.commodities = commodities;
    }

    public ArrayList<CustomerPO> getCustomes() {
        return customes;
    }

    public void setCustomes(ArrayList<CustomerPO> customes) {
        this.customes = customes;
    }

    public ArrayList<AccountPO> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<AccountPO> accounts) {
        this.accounts = accounts;
    }
}
