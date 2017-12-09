package nju.lighting.po.repository;

import shared.RepositoryChangeType;

import java.io.Serializable;
import java.util.Date;

public class RepositoryChangePO implements Serializable {
    private static final long serialVersionUID = 5663833142940508609L;
    private int id;

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

    public RepositoryChangePO(int id, String commodityId, RepositoryChangeType type, int count, double amount, Date date) {
        this.id = id;
        this.commodityId = commodityId;
        this.type = type;
        this.count = count;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
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
