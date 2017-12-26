package nju.lighting.data.commoditydata;

import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import shared.ResultMessage;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommodityDataService_Stub implements CommodityDataService {

    private ArrayList<CommodityItemPO> commodityItemPOS;

    private ArrayList<CommodityCategoryPO> commodityCategoryPOS;

    public CommodityDataService_Stub() {
        CommodityItemPO com1 = new CommodityItemPO("01", "天才灯具", 1, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemPO com2 = new CommodityItemPO("02", "天才灯炮", 1, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemPO com3 = new CommodityItemPO("03", "小天才灯具", 2, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemPO com4 = new CommodityItemPO("04", "小天才灯炮", 2, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemPO com5 = new CommodityItemPO("05", "大天才灯具", 3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        CommodityItemPO com6 = new CommodityItemPO("06", "大天才灯具", 3, "2017",
                100, 1000, 1000, 1000, 1000, "a", "01", new Date(111111111));
        commodityItemPOS = new ArrayList<>();
        commodityItemPOS.add(com1);
        commodityItemPOS.add(com2);
        commodityItemPOS.add(com3);
        commodityItemPOS.add(com4);
        commodityItemPOS.add(com5);
        commodityItemPOS.add(com6);

        CommodityCategoryPO cc1 = new CommodityCategoryPO(1, "天才", -1);
        CommodityCategoryPO cc2 = new CommodityCategoryPO(2, "小天才", -1);
        CommodityCategoryPO cc3 = new CommodityCategoryPO(1, "大天才", -1);
        CommodityCategoryPO cc4 = new CommodityCategoryPO(1, "超级天才", -1);
        commodityCategoryPOS = new ArrayList<>();
        commodityCategoryPOS.add(cc1);
        commodityCategoryPOS.add(cc2);
        commodityCategoryPOS.add(cc3);
        commodityCategoryPOS.add(cc4);
    }

    @Override
    public List<CommodityItemPO> findByCategory(int categoryID) throws RemoteException {
        return null;
    }

    @Override
    public Date getRecentChangeTime() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CommodityItemPO> getAllCommodity() {
        return commodityItemPOS;
    }

    @Override
    public CommodityItemPO findById(String id) {
        for (int i = 0; i < commodityItemPOS.size(); i++) {
            if (id.equals(commodityItemPOS.get(i).getId()))
            return commodityItemPOS.get(i);
        }
        return null;
    }

    @Override
    public CommodityCategoryPO findCategoryById(int id) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(CommodityItemPO commodityItemPO) {
        String id = commodityItemPO.getId();
        for (int i = 0; i < commodityItemPOS.size(); i++) {
            if (id.equals(commodityItemPOS.get(i).getId()))
                return ResultMessage.FAILURE;
        }
        commodityItemPOS.add(commodityItemPO);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(CommodityCategoryPO categoryPO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(CommodityItemPO commodityItemPO) {
        String id = commodityItemPO.getId();
        for (int j = 0; j < commodityItemPOS.size(); j++) {
            if (id.equals(commodityItemPOS.get(j).getId())) {
                commodityItemPOS.remove(j);
                commodityItemPOS.add(commodityItemPO);
                return ResultMessage.SUCCESS;
            }
        }
        return ResultMessage.FAILURE;
    }

    @Override
    public ResultMessage deleteCommodity(String id) {
        for (int i = 0; i < commodityItemPOS.size(); i++) {
            if (id.equals(commodityItemPOS.get(i).getId())) {
                commodityItemPOS.remove(i);
                return ResultMessage.SUCCESS;
            }
        }
        return ResultMessage.FAILURE;
    }

    @Override
    public ArrayList<CommodityCategoryPO> getAllCommodityCategory() {
        return commodityCategoryPOS;
    }

    @Override
    public TwoTuple<ResultMessage, Integer> add(CommodityCategoryPO commodityCategoryPO) {
//        int catId = commodityCategoryPO.getId();
//        for (int i = 0; i < commodityCategoryPOS.size(); i++) {
//            if (commodityCategoryPOS.get(i).getId() == catId)
//                return ResultMessage.FAILURE;
//        }
//        commodityCategoryPOS.add(commodityCategoryPO);
        return null;
    }

    @Override
    public ResultMessage deleteCategory(int id) {
        for (int i = 0; i < commodityItemPOS.size(); i++) {
            if (commodityItemPOS.get(i).getCategoryId() == id)
                return ResultMessage.FAILURE;
        }
        for (int i = 0; i < commodityCategoryPOS.size(); i++) {
            if (commodityCategoryPOS.get(i).getId() == id) {
                commodityCategoryPOS.remove(i);
                return ResultMessage.SUCCESS;
            }
        }
        return ResultMessage.FAILURE;
    }

    @Override
    public ArrayList<CommodityItemPO> findByName(String name) {
        ArrayList<CommodityItemPO> pos = new ArrayList<>();
        for (int i = 0; i < commodityItemPOS.size(); i++) {
            if (name.equals(commodityItemPOS.get(i).getName())) {
                pos.add(commodityItemPOS.get(i));
            }
        }
        return pos;
    }

    @Override
    public List<CommodityItemPO> fuzzySearchByName(String key) throws RemoteException {
        return null;
    }

    @Override
    public List<CommodityItemPO> fuzzySearchById(String key) throws RemoteException {
        return null;
    }

    @Override
    public List<CommodityItemPO> fuzzySearchByModel(String key) throws RemoteException {
        return null;
    }
}
