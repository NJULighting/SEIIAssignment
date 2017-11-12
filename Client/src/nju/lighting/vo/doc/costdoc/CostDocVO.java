package nju.lighting.vo.doc.costdoc;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.account.AccountVO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class CostDocVO extends DocVO {
    private ArrayList<AccountVO> accountList; // 用于提供账户列表，方便用户选择
    private AccountVO account; // 用户最后在现金费用单中选择的账户
    private ArrayList<CostDocItemVO> itemList;
    private int total;

    public CostDocVO(Date time, String creatorId, String docId
            , AccountVO accounts, ArrayList<CostDocItemVO> itemList, int total, ArrayList<AccountVO> accountList) {
        super(time, creatorId, docId, DocType.COST);
        this.account = accounts;
        this.itemList = itemList;
        this.total = total;
        this.accountList = accountList;
    }

    public ArrayList<AccountVO> getAccountList() {
        return accountList;
    }

    public CostDocVO(Date time, String creatorId, String docId) {
        super(time, creatorId, docId, DocType.COST);
    }

    public AccountVO getAccount() {
        return account;
    }

    public ArrayList<CostDocItemVO> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<CostDocItemVO> itemList) {
        this.itemList = itemList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
