package nju.lighting.dataservice.customerdataservice;

import nju.lighting.po.CustomerPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CustomerDataService {

    public ArrayList<CustomerPO> getAll();

    public CustomerPO find(String keyword);

    public void insert(CustomerPO po);

    public void delete(CustomerPO po);

    public void update(CustomerPO po);

    public void init();

    public void finish();

}
