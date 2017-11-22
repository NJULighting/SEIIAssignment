package nju.lighting.dataservice;

import nju.lighting.dataservice.accountdataservice.AccountDataService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class DataFactory {
    public static AccountDataService getAccountDataBase() throws NamingException {
        Context namingContext = new InitialContext();
        return (AccountDataService) namingContext.lookup("rmi://localhost:8888/accountDataService");
    }
}
