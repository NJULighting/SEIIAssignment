package nju.lighting.data.documentdata;

import nju.lighting.po.doc.accountiodoc.AccountIODocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class AccountIODocData extends AbstractDocData<AccountIODocPO> {

    private String docName = "SKD";

    public AccountIODocData() {
        super(AccountIODocPO.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
