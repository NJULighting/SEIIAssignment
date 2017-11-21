package nju.lightingServer.po.repository;

import shared.RepositoryChangeType;

import java.util.Date;

public class RepositoryChangePO {

    private String commodityId;

    private RepositoryChangeType type;

    private int count;

    private double amount;

    private Date date;

    public RepositoryChangePO(String commodityId, RepositoryChangeType type, int count, double amount, Date date) {
        this.commodityId = commodityId;
        this.type = type;
        this.count = count;
        this.amount = amount;
        this.date = date;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public RepositoryChangeType getType() {
        return type;
    }

    public void setType(RepositoryChangeType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
