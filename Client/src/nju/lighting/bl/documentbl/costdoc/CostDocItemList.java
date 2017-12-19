package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.costdoc.CostDocItemPO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/12/18.
 * Description:
 * @author Liao
 */
public class CostDocItemList extends ItemList<CostDocItem>{

    void add(CostDocItemPO po) {
        add(po, CostDocItem::new);
    }

    void add(CostDocItemVO vo) {
        add(vo, CostDocItem::new);
    }


    @Override
    public List<DocPO> toPO(String docId) {
        return null;
    }
}
