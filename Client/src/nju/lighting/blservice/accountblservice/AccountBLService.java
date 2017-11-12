package nju.lighting.blservice.accountblservice;

import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/20.
 * Description:
 * @author Liao
 */
public interface AccountBLService {
    ArrayList<AccountVO> getAccountList() ;

    ResultMessage addAccount(String name, String amount) ;

    ArrayList<AccountVO> findAccounts(String keyword) ;

    AccountVO getAccount(String id) ;

    ResultMessage deleteAccount(String id) ;

    ResultMessage modifyAccount(String oldName, String newName) ;
}
