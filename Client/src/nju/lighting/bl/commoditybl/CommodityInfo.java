package nju.lighting.bl.commoditybl;

import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.repository.RepositoryTableItemVO;
import shared.ResultMessage;

import java.util.List;

public interface CommodityInfo {

    BasicCommodityItemVO getBasicCommodityItemVO(String id);

    double getCommodityRecentSellPrice(String id);

    String getCommodityNameByID(String commodityID);

    /**
     * Get name of the commodity's category. If the commodity is the
     * child of the root category(id is -1), it won't query the database.
     * @param commodityID id of the commodity
     * @return name of the category
     */
    String getCommodityCategory(String commodityID);

    double getCommodityInPrice(String id);

    double getCostAdjustRevenue();

    List<RepositoryTableItemVO> getRepositoryTableItemList();

    ResultMessage changeCommodityNumber(String id, int count);

    boolean reduceCommodityItem(String id, int count);

    boolean achieveAlertLimit(String commodityId, int count);
}
