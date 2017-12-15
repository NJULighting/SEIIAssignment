package nju.lighting.bl.documentbl.accountiodoc;

import shared.ResultMessage;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
public class OutStrategy implements InOutStrategy {
    @Override
    public void approve(AccountIODoc ioDoc) {
    }

    @Override
    public ResultMessage reject(AccountIODoc ioDoc) {
        return null;
    }
}
