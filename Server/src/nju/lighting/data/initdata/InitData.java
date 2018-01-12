package nju.lighting.data.initdata;

import nju.lighting.data.accountdata.AccountData;
import nju.lighting.data.commoditydata.CommodityData;
import nju.lighting.data.customerdata.CustomerData;
import nju.lighting.data.utils.CommonOperation;
import nju.lighting.data.utils.Zipper;
import nju.lighting.dataservice.initdataservice.InitDataService;
import nju.lighting.po.account.AccountPO;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.po.customer.CustomerPO;
import nju.lighting.po.init.InitPO;
import shared.CSVable;
import shared.Result;
import shared.ResultMessage;

import java.io.File;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/11/27.
 * Description:
 *
 * @author iznauy
 */
public class InitData extends UnicastRemoteObject implements InitDataService {

    private CommonOperation<InitPO> commonOperation;

    public InitData() throws RemoteException {
        commonOperation = new CommonOperation<>(InitPO.class.getName());
    }

    private void createCSVFile(String url) throws Exception {
        File file = new File(url);
        if (file.exists()) {
            return;
        }
        if (!file.mkdir())
            return;
        CommodityData commodityData = new CommodityData();
        List<CommodityItemPO> commodityItemPOS = commodityData.getAllCommodity();
        AccountData accountData = new AccountData();
        List<AccountPO> accountPOS = accountData.getAll();
        CustomerData customerData = new CustomerData();
        List<CustomerPO> customerPOS = customerData.getAllCustomer();
        writeTData(url + "/commodity.csv", commodityItemPOS);
        writeTData(url + "/account.csv", accountPOS);
        writeTData(url + "/customer.csv", customerPOS);
    }

    private <T extends CSVable> void writeTData(String url, List<T> data) throws Exception {
        File tFile = new File(url);
        if (!tFile.createNewFile())
            throw new Exception();
        FileWriter fileWriter = new FileWriter(tFile);
        boolean first = true;
        for (T t: data) {
            if (first) {
                fileWriter.write(t.getClassDescription());
                fileWriter.write(System.lineSeparator());
                first = false;
            }
            fileWriter.write(t.toCSV());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    @Override
    public Result<InitPO> createInit(String userId, Date date) throws RemoteException {
        String url = date.toString().replace(" ", "");
        url = url.replace(":", "");

        InitPO initPO = new InitPO(date, userId, "http://localhost:8080/init/" + url + ".zip");
        url = "C:\\Users\\liao\\Desktop\\init\\" + url;
        try {
            createCSVFile(url);
        } catch (Exception e) {
            e.printStackTrace();
            File file = new File(url);
            if (file.exists()) {
                file.delete();
            }
            return new Result<>(ResultMessage.FAILURE, null);
        }

        Zipper zipper = new Zipper(url + ".zip", url);
        try {
            zipper.zip();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultMessage.FAILURE, null);
        }
        ResultMessage resultMessage = commonOperation.add(initPO);
        return new Result<>(resultMessage, initPO);
    }

    @Override
    public List<InitPO> getAllInit() throws RemoteException {
        return commonOperation.getAll();
    }


}
