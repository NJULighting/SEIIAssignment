package nju.lighting.dataservice;

import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.dataservice.userdataservice.UserDataService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class DataFactory {

    private static Map<Class, String> databaseMap = new HashMap<>();

    static {
        databaseMap.put(UserDataService.class, "userDataService");
        databaseMap.put(AccountDataService.class, "accountDataService");
    }
    public static AccountDataService getAccountDataBase() throws NamingException {
        Context namingContext = new InitialContext();
        return (AccountDataService) namingContext.lookup("rmi://localhost:8888/accountDataService");
    }

    public static <R> R getDataBase(Class<R> type) throws NamingException {
        Context namingContext = new InitialContext();
        return type.cast(namingContext.lookup("rmi://localhost:8888/" + databaseMap.get(type)));
    }
}
