package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.commodity.BasicCommodityTreeVO;
import nju.lighting.vo.commodity.CommodityItemVO;
import nju.lighting.vo.commodity.CommodityTreeVO;

import java.util.ArrayList;

public interface CommodityInfo {

    CommodityTreeVO getCommodityTree();

    BasicCommodityTreeVO getBasicCommodityTree();

    ArrayList<BasicCommodityItemVO> getBasicCommodityItems(ArrayList<String> ids);

    ArrayList<CommodityItemVO> getCommodityItems(ArrayList<String> ids);

}
