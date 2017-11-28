package nju.lighting.bl.accountbl;

import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/11/7.
 * Description: 该接口为其他需要账户信息的模块提供服务
 * @author Liao
 */
public interface AccountInfo {
    ArrayList<Account> getAccountList();

    ResultMessage updateAmount(String targetName, double total);
}
