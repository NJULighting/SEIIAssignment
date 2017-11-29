package nju.lighting.main;

import nju.lighting.data.accountdata.AccountData;
import nju.lighting.data.accountdata.AccountDataService_Stub;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.po.user.UserPO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class Server {
    public static void main(String[] args) {
        System.out.println("Constructing server implementation");
        try {
            AccountDataService accountDataService = new AccountData();
            Context namingContext = new InitialContext();
            LocateRegistry.createRegistry(8888);
            namingContext.bind("rmi://localhost:8888/accountDataService", accountDataService);
        } catch (RemoteException | NamingException e) {
            e.printStackTrace();
        }
    }
}
