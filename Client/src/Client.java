import nju.lighting.dataservice.DataFactory;

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
        } catch (RemoteException | NamingException e) {
            e.printStackTrace();
        }
    }
}
