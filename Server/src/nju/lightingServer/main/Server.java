package nju.lightingServer.main;

import nju.lightingServer.data.accountdata.AccountDataService_Stub;
import nju.lighting.dataservice.accountdataservice.AccountDataService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class Server {
    public static void main(String[] args) {
        System.out.println("Constructing server implementation");
        try {
            AccountDataService accountDataService = new AccountDataService_Stub();
            Context namingContext = new InitialContext();
            namingContext.bind("rmi://localhost/accountDataService", accountDataService);
        } catch (RemoteException | NamingException e) {
            e.printStackTrace();
        }
    }
}
