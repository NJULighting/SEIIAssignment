package nju.lighting.data.initdata;

import nju.lighting.data.accountdata.AccountData;
import nju.lighting.data.commoditydata.CommodityData;
import nju.lighting.data.customerdata.CustomerData;
import nju.lighting.data.utils.CommonOperation;
import nju.lighting.dataservice.initdataservice.InitDataService;
import nju.lighting.po.account.AccountPO;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.po.customer.CustomerPO;
import nju.lighting.po.init.InitPO;
import shared.CSVable;
import shared.ResultMessage;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

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
            System.out.println(url);
            return;
        }
        if (!file.mkdir())
            return;
        System.out.println("---------------" + file.getAbsolutePath() + "-----------------");
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
    public ResultMessage createInit(String userId, Date date) throws RemoteException {
        String url = date.toString().replace(" ", "_");
        try {
            createCSVFile(url);
        } catch (Exception e) {
            e.printStackTrace();
            File file = new File(url);
            if (file.exists())
                file.delete();
            return ResultMessage.FAILURE;
        }
        zip(new File(url), url + ".zip");
        InitPO initPO = new InitPO(date, userId, url + ".zip");
        System.out.println("Before Return");
        return commonOperation.add(initPO);
    }

    @Override
    public List<InitPO> getAllInit() throws RemoteException {
        return commonOperation.getAll();
    }

    /**
     * 以下代码为网上抄的压缩文件代码
     * @param inputFile
     * @param zipFileName
     */
    public static void zip(File inputFile, String zipFileName) {
        try {
            FileOutputStream out = new FileOutputStream(
                    new String(zipFileName.getBytes("UTF-8")));
            ZipOutputStream zOut = new ZipOutputStream(out);
            zip(zOut, inputFile, "");
            zOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void zip(ZipOutputStream zOut, File file, String base) {
        try {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                zOut.putNextEntry(new ZipEntry(base + "/"));
                base = (base.length() == 0 ? "" : base + "/");
                for (int i = 0; i < listFiles.length; i++) {
                    zip(zOut, listFiles[i], base + listFiles[i].getName());
                }
            }
            else {
                if (base == "") {
                    base = file.getName();
                }
                zOut.putNextEntry(new ZipEntry(base));
                writeFile(zOut,file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void writeFile(ZipOutputStream zOut,File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        int len;
        while ((len = in.read()) != -1)
            zOut.write(len);
        in.close();
    }

}
