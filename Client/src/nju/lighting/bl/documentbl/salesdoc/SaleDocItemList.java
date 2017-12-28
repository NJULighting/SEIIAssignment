package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;
import shared.ResultMessage;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

/**
 * Created on 2017/12/21.
 * Description:
 * @author Liao
 */
class SaleDocItemList {

    private SalesDocItemType itemType;
    private ItemList<SalesDocItem> itemList = new ItemList<>();

    SaleDocItemList(SalesDocItemType itemType) {
        this.itemType = itemType;
    }

    void add(SalesDocItemPO po) {
        itemList.add(po, item -> new SalesDocItem(item, itemType));
    }

    void add(SalesDocItemVO vo) {
        itemList.add(vo, item -> new SalesDocItem(item, itemType));
    }

    boolean containsCommodity(String commodityName) {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return itemList.containItemWithAttribute(commodityName,
                item -> commodityInfo.getCommodityNameByID(item.getCommodityID()));
    }

    List<SalesDocItemPO> toPO(String docId) {
        return itemList.toPO(item -> item.toPO(docId));
    }

    List<SalesDocItemVO> toVO() {
        return itemList.toVO(SalesDocItem::toVO);
    }

    List<BusinessConditionItemVO> toBusinessConditionItemVO(Date date) {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        Function<SalesDocItem, BusinessConditionItemVO> function = item -> new BusinessConditionItemVO(date,
                commodityInfo.getCommodityNameByID(item.getCommodityID()),
                commodityInfo.getCommodityCategory(item.getCommodityID()),
                item.getNumber(), item.getSalePrice(), item.getTotalAmount());
        return itemList.transform(function);
    }

    /**
     * Get total cost of this sale.(Sum of these commodities' purchase price)
     */
    double getTotalCost() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        ToDoubleFunction<SalesDocItem> function =
                doc -> commodityInfo.getBasicCommodityItemVO(doc.getCommodityID()).getRecentInPrice() * doc.getNumber();
        return itemList.transformAndSum(function);
    }

    public void redFlush() {
        itemList.redFlush();
    }

    ResultMessage approve() {
        return itemList.approve();
    }
}