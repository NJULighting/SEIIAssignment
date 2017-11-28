package nju.lighting.data.documentdata;

import nju.lighting.po.doc.costdoc.CostDocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class CostDocData extends AbstractDocData<CostDocPO> {

    private String docName = "XJFYD";

    public CostDocData() {
        super(CostDocData.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
