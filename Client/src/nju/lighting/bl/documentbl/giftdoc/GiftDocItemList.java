package nju.lighting.bl.documentbl.giftdoc;

import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.giftdoc.GiftItemPO;
import nju.lighting.vo.doc.giftdoc.GiftItemVO;

import java.util.List;

/**
 * Created on 2017/12/21.
 * Description:
 * @author Liao
 */
public class GiftDocItemList {


    private ItemList<GiftDocItem> itemList;
    void add(GiftItemPO po) {
        itemList.add(po, GiftDocItem::new);
    }

    void add(GiftItemVO vo) {
        itemList.add(vo, GiftDocItem::new);
    }

    List<GiftItemPO> toPO(String docId) {
        return itemList.toPO(docId, item -> item.toPO(docId));
    }
}
