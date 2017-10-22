package nju.lighting.dataservice.customerdataservice;

import nju.lighting.po.CustomerPO;

import java.util.ArrayList;

public interface CustomerDataService {

    public ArrayList<CustomerPO> getAll();

    public CustomerPO find(String keyword);

    public void insert(CustomerPO po);

    public void delete(CustomerPO po);

    public void update(CustomerPO po);

}
