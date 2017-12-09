package nju.lighting.bl.commoditybl;

import nju.lighting.bl.logbl.Logger;
import nju.lighting.bl.logbl.UserLogger;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.commoditydataservice.CommodityDataService;
import nju.lighting.po.commodity.CommodityCategoryPO;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

enum CommodityManager implements CommodityInfo {
    INSTANCE;

    private CommodityCategoriesTree commodityTree;
    private CommodityDataService dataService;
    private Logger logger;

    CommodityManager() {
        try {
            dataService = DataFactory.getDataBase(CommodityDataService.class);
            logger = new UserLogger();
            buildCommodityTree();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private void buildCommodityTree() {
        try {
            List<CommodityCategoryPO> categoryPOList = dataService.getAllCommodityCategory();
            CommodityCategoriesTree.TreeBuilder treeBuilder = new CommodityCategoriesTree.TreeBuilder(categoryPOList);
            commodityTree = treeBuilder.build();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对逻辑层其他模块提供的方法
     */
    public CommodityCategoriesTree getCommodityTree() {
        return null;
    }

    public BasicCommodityTree getBasicCommodityTree() {
        return null;
    }

    public ArrayList<BasicCommodityItem> getBasicCommodityItems(ArrayList<String> ids) {
        return null;
    }

    public ArrayList<CommodityItem> getCommodityItems(ArrayList<String> ids) {
        return null;
    }


    // 对界面层提供的方法
    public CommodityTreeVO getCommodityTreeVO() {

        return null;
    }

    public ResultMessage addCommodity(CommodityItemVO newCommodity) {
        return null;
    }

    public ArrayList<CommodityItemVO> findCommodityVOByName(String commodityName) {
        return null;
    }

    public CommodityItemVO findCommodityVOById(String id) {
        return null;
    }

    public ResultMessage deleteCommodity(String id) {
        return null;
    }

    public ResultMessage modifyCommodity(CommodityItemVO commodity) {
        return null;
    }

    public ResultMessage addCategory(CommodityCategoryVO newCategory) {
        return null;
    }

    public ResultMessage deleteCategory(int id) {
        return null;
    }

    public ResultMessage modifyCategory(CommodityCategoryVO categoryItemVO) {
        return null;
    }

    @Override
    public boolean addCommodityItem(String id, int count) {
        return false;
    }

    @Override
    public boolean subCommodityItem(String id, int count) {
        return false;
    }
}
