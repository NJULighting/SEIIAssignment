package nju.lighting.bl.accountbl;

/**
 * Created on 2017/12/26.
 * Description:
 * @author Liao
 */
public enum Accounts {
    BBIDA_CARD("284983325"), AIRON_CARD("924342513");

    private String accountId;

    Accounts(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}
