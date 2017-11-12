package nju.lighting.bl.documentbl;

import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import shared.DocType;

import java.util.Date;

/**
 * Created on 2017/11/7.
 * Description: 负责创建初始的现金费用单VO
 * @author Liao
 */
public class CostDoc extends Doc{

    public CostDoc(String id, String userId, Date time) {
        super(id, DocType.COST, userId, time);
    }

    @Override
    void approve() {

    }

    @Override
    DocVO createVO() {
        return null;
    }

    @Override
    DocPO createPO() {
        return null;
    }
}
