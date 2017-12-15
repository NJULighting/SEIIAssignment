package nju.lighting.po.doc.accountiodoc;

import shared.Item;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
@Entity
@Table(name = "ACCOUNT_TRANSFER")
public class AccountTransferItemPO implements Item, Serializable {

    private static final long serialVersionUID = -6496549992772237320L;
    private int id;

    private String accountID;

    private String docId;

    private double amount;

    private String comments;

    public AccountTransferItemPO() {

    }

    public AccountTransferItemPO(int id, String accountID, String docId, double amount, String comments) {
        this.id = id;
        this.accountID = accountID;
        this.docId = docId;
        this.amount = amount;
        this.comments = comments;
    }

    @Column(name = "ACCOUNT_IO_DOC_ID", nullable = false, length = 36)
    public String getDocId() {
        return docId;
    }

    public void setDocId(String accountIODocID) {
        this.docId = accountIODocID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ACCOUNT_ID", nullable = false, length = 20)
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    @Column(name = "AMOUNT", nullable = false)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Column(name = "COMMENT", length = 300)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
