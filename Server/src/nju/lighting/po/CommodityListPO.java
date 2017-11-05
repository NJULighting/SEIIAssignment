package nju.lighting.po;

import java.util.ArrayList;

public class CommodityListPO {

    private ArrayList<CommodityListItemPO> commodityList;

    private double totalAmount = 0;

    public void addCommoditysListItem(CommodityListItemPO commodityListItem) {

        totalAmount = totalAmount + commodityListItem.getTotalAmount();
        commodityList.add(commodityListItem);

    }

    public void removeCommoditysListItem(CommodityListItemPO commodityListItem) {

        totalAmount = totalAmount - commodityListItem.getTotalAmount();
        commodityList.remove(commodityListItem);

    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
