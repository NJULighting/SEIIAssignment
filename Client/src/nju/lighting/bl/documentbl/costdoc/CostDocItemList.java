package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.costdoc.CostDocItemPO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
public class CostDocItemList {
    private ItemList<CostDocItem> itemList = new ItemList<>();

    void add(CostDocItemPO po) {
        itemList.add(po, CostDocItem::new);
    }

    void add(CostDocItemVO vo) {
        itemList.add(vo, CostDocItem::new);
    }

    List<CostDocItemPO> toPo(String docId) {
        return itemList.toPO(docId, item -> item.toPO(docId));
    }

    List<CostDocItemVO> toVO() {
        return itemList.toVO(CostDocItem::toVO);
    }
}
