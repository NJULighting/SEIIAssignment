package nju.lighting.po.repository;

import shared.RepositoryChangeType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REPOSITORY_CHANGE")
public class RepositoryChangePO {

    private int id;

    private String commodityId;

    private RepositoryChangeType type;

    private int count;

    private double amount;

    private Date date;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id);
        builder.append(" ");
        builder.append(commodityId);
        builder.append(" ");
        builder.append(type);
        builder.append(" ");
        builder.append(count);
        builder.append(" ");
        builder.append(amount);
        builder.append(" ");
        builder.append(date);
        return builder.toString();
    }

    public RepositoryChangePO() {

    }

    public RepositoryChangePO(String commodityId, RepositoryChangeType type, int count, double amount, Date date) {
        this.commodityId = commodityId;
        this.type = type;
        this.count = count;
        this.amount = amount;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "COMMODITY_ID", length = 50, nullable = false)
    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", length = 20, nullable = false)
    public RepositoryChangeType getType() {
        return type;
    }

    public void setType(RepositoryChangeType type) {
        this.type = type;
    }

    @Column(name = "COUNT", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Column(name = "AMOUNT", nullable = false)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Column(name = "CHANGE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
