package nju.lighting.presentation.mainui;

import nju.lighting.vo.commodity.BasicCommodityItemVO;

import java.util.List;

/**
 * Created on 2017/12/25.
 * Description
 *
 * @author 陈俊宇
 */
public interface CommodityUpper extends Upper {
    public void addCommodity(List<BasicCommodityItemVO> commodity);
}
