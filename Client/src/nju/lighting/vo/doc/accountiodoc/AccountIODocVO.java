package nju.lighting.vo.doc.accountiodoc;

import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.DocVO;
import shared.AccountIODocType;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/10/21.
 * Description:
 *
 * @author Liao
 */
public class AccountIODocVO extends DocVO {
    private AccountIODocType type;
    private String customer;
    private List<AccountTransferItemVO> transferAccountList;
    private double total;

    public AccountIODocVO(Date time, String creatorId, String docId, DocType type, AccountIODocType type1, String customer,
                          List<AccountTransferItemVO> transferAccountList, double total) {
        super(time, creatorId, docId, type);
        this.type = type1;
        this.customer = customer;
        this.transferAccountList = transferAccountList;
        this.total = total;
    }

    //无需输入总价的构造函数
    public AccountIODocVO(Date time, String creatorId, String docId, DocType type, AccountIODocType type1, String customer,
                          List<AccountTransferItemVO> transferAccountList) {
        super(time, creatorId, docId, type);
        this.type = type1;
        this.customer = customer;
        this.transferAccountList = transferAccountList;

        //学习 骚操作
//        List<List<String>> l = new ArrayList<>();
//        List<String> l1 = l.stream().flatMap(x -> x.stream()).collect(Collectors.toList());


        total = transferAccountList.stream()
                .mapToDouble(AccountTransferItemVO::getAmount)
                .sum();
    }

    public AccountIODocType getAccountIODdocType() {
        return type;
    }

    public void setAccountIODocType(AccountIODocType type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<AccountTransferItemVO> getTransferAccountList() {
        return transferAccountList;
    }

    public void setTransferAccountList(List<AccountTransferItemVO> transferAccountList) {
        this.transferAccountList = transferAccountList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
