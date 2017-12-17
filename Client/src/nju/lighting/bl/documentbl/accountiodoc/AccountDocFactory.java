package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.accountbl.AccountInfo;
import nju.lighting.bl.accountbl.AccountInfoImpl;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.DocFactory;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountIODocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocType;

import java.util.Date;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
public class AccountDocFactory implements DocFactory {

    @Override
    public DocVO getVOForCreation() {
        AccountInfo accountInfo = new AccountInfoImpl();
        return new AccountIODocVO(new Date(), DocType.ACCOUNT_IN, accountInfo.getAccountList());
    }

    @Override
    public Doc createDocForApproval(HistoryDocVO historyDocVO) {
        return null;
    }
}
