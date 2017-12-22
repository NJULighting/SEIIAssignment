package nju.lighting.bl.documentbl.salesdoc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;

import java.util.List;

/**
 * Created on 2017/12/21.
 * Description:
 * @author Liao
 */
class SaleDocItemList   {

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
}
