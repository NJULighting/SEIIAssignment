package nju.lighting.po.repository;

import java.util.Date;

public class RepositoryChangePO {

    private int id;

    private String commodityId;

    private int count;

    private double amount;

    private Date time;

    private RepositoryChangeType type;

    public RepositoryChangePO(int id, String commodityId, int count,
                              double amount, Date time, RepositoryChangeType type) {
        this.id = id;
        this.commodityId = commodityId;
        this.count = count;
        this.amount = amount;
        this.time = time;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public RepositoryChangeType getType() {
        return type;
    }

    public void setType(RepositoryChangeType type) {
        this.type = type;
    }
}
