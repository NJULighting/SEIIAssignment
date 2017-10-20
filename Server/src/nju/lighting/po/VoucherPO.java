package nju.lighting.po;

import java.util.Date;

/**
 * Created on 2017/10/19.
 * Description:代金券
 *
 * @author 陈俊宇
 */
public class VoucherPO {
    private Date endDate;

    private double amount;

    public VoucherPO(Date endDate,double amount){
        this.amount=amount;
        this.endDate=endDate;
    }


    public Date getEndDate(){
        return endDate;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount=amount;
    }

    public void setEndDate(Date endDate){
        this.endDate=endDate;
    }
}
