import nju.lighting.bl.accountbl.Account;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.po.account.AccountPO;

import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Created on 2017/11/12.
 * Description:
 * @author Liao
 */
public class Client {
    public static void main(String[] args) {
        try {
            System.out.println(DataFactory.getAccountDataBase().insert(null));
            AccountPO po = DataFactory.getAccountDataBase().get("01");
        } catch (RemoteException | NamingException e) {
            e.printStackTrace();
        }
    }
}
