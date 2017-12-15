package nju.lighting.bl.documentbl.accountiodoc;

import shared.ResultMessage;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
interface InOutStrategy {
    void approve(AccountIODoc ioDoc);

    ResultMessage reject(AccountIODoc ioDoc);
}
