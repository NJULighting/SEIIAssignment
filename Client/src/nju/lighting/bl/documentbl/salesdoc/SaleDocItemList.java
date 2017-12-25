package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import nju.lighting.vo.viewtables.BusinessConditionItemVO;

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

    private ItemList<SalesDocItem> itemList = new ItemList<>();

    void add(SalesDocItemPO po) {
        itemList.add(po, SalesDocItem::new);
    }

    void add(SalesDocItemVO vo) {
        itemList.add(vo, SalesDocItem::new);
    }

    boolean containsCommodity(String commodityName) {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        return itemList.containItemWithAttribute(commodityName,
                item -> commodityInfo.getCommodityNameByID(item.getCommodityID()));
    }

    List<SalesDocItemPO> toPO(String docId) {
        return itemList.toPO(docId, item -> item.toPO(docId));
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
        return itemList.transformItemToObject(function);
    }

    double getTotalRevenue() {
        ToDoubleFunction<SalesDocItem> function = SalesDocItem::getTotalAmount;
        return itemList.transformItemToNumber(function);
    }

    double getTotalCost() {
        CommodityInfo commodityInfo = new CommodityInfoImpl();
        ToDoubleFunction<SalesDocItem> function =
                doc -> commodityInfo.getBasicCommodityItemVO(doc.getCommodityID()).getRecentInPrice() * doc.getNumber();
        return itemList.transformItemToNumber(function);
    }

    public void redFlush() {
        itemList.redFlush();
    }
}