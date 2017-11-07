package nju.lighting.vo;

import java.util.ArrayList;

public class CommodityListVO {

    private ArrayList<CommodityListItemVO> commodityList;

    private double totalAmount = 0;

    public void addCommoditysListItem(CommodityListItemVO commodityListItem){

        totalAmount = totalAmount + commodityListItem.getTotalAmount();
        commodityList.add(commodityListItem);

    }
    public void removeCommoditysListItem(CommodityListItemVO commodityListItem){

        totalAmount = totalAmount - commodityListItem.getTotalAmount();
        commodityList.remove(commodityListItem);

    }
    public double getTotalAmount(){
        return totalAmount;
    }
}
