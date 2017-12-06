package nju.lighting.main;

import nju.lighting.data.accountdata.AccountData;
import nju.lighting.data.customerdata.CustomerData;
import nju.lighting.data.initdata.InitData;
import nju.lighting.data.logdata.LogData;
import nju.lighting.data.userdata.UserData;
import nju.lighting.dataservice.accountdataservice.AccountDataService;
import nju.lighting.dataservice.customerdataservice.CustomerDataService;
import nju.lighting.dataservice.initdataservice.InitDataService;
import nju.lighting.dataservice.logdataservice.LogDataService;
import nju.lighting.dataservice.userdataservice.UserDataService;

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
    private static final String ADDRESS = "rmi://localhost:8888/";
    public static void main(String[] args) {
        System.out.println("Constructing server implementation");
        try {
            AccountDataService accountDataService = new AccountData();
            UserDataService userDataService = new UserData();
            LogDataService logDataService = new LogData();
            CustomerDataService customerDataService = new CustomerData();
            InitDataService initDataService = new InitData();
            Context namingContext = new InitialContext();
            LocateRegistry.createRegistry(8888);
            namingContext.bind(ADDRESS + "initDataService", initDataService);
            namingContext.bind(ADDRESS + "customerDataService", customerDataService);
            namingContext.bind(ADDRESS + "logDataService", logDataService);
            namingContext.bind(ADDRESS + "accountDataService", accountDataService);
            namingContext.bind(ADDRESS + "userDataService", userDataService);
        } catch (RemoteException | NamingException e) {
            e.printStackTrace();
        }
    }
}
