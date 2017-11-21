package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;
import shared.ResultMessage;

import java.util.ArrayList;

public class CommodityManager implements CommodityInfo {

    /**
     * 商品树
     */
    private CommodityTree commodityTree;

    /**
     * 单例
     */
    private static CommodityManager commodityManager;

    /**
     * 构造器 初始化
     */
    private CommodityManager() {
        fetchCommodityInfo();
    }

    /**
     * 单例类
     */
    public static CommodityManager createCommodity() {
        if (CommodityManager.commodityManager == null)
            CommodityManager.commodityManager = new CommodityManager();
        return CommodityManager.commodityManager;
    }

    /**
     * 去服务器端取数据
     */
    private void fetchCommodityInfo() {

    }

    /**
     * 判断服务器端数据是否发生变化
     */
    private boolean hasChanged() {
        return false;
    }

    /**
     * 对逻辑层其他模块提供的方法
     */
    public CommodityTree getCommodityTree() {
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

    /**
     * 对界面层提供的方法
     */
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
