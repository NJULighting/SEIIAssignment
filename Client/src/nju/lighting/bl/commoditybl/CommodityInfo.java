package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.BasicCommodityTreeVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;

import java.util.ArrayList;

public interface CommodityInfo {

    CommodityTree getCommodityTree();

    BasicCommodityTree getBasicCommodityTree();

    ArrayList<BasicCommodityItem> getBasicCommodityItems(ArrayList<String> ids);

    ArrayList<CommodityItem> getCommodityItems(ArrayList<String> ids);

    boolean addCommodityItem(String id, int count);

    boolean subCommodityItem(String id, int count);

}
