package nju.lighting.po;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/17.
 * Description:
 * @author Liao
 */
public class InitPO {
    private String id;
    private Date time;
    private String userID;
    private ArrayList<String> commodityCategories;
    private ArrayList<String> commodities;
    private ArrayList<String> customers;
    private ArrayList<String> accounts;

    public InitPO(ArrayList<String> commodityCategories, ArrayList<String> commodities, ArrayList<String> customers, ArrayList<String> accounts) {
        this.commodityCategories = commodityCategories;
        this.commodities = commodities;
        this.customers = customers;
        this.accounts = accounts;
    }

    public ArrayList<String> getCommodityCategories() {
        return commodityCategories;
    }

    public void setCommodityCategories(ArrayList<String> commodityCategories) {
        this.commodityCategories = commodityCategories;
    }

    public ArrayList<String> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<String> commodities) {
        this.commodities = commodities;
    }

    public ArrayList<String> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<String> customers) {
        this.customers = customers;
    }

    public ArrayList<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<String> accounts) {
        this.accounts = accounts;
    }
}
