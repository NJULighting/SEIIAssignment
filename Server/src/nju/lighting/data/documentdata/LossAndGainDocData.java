package nju.lighting.data.documentdata;

import nju.lighting.po.doc.lossandgaindoc.LossAndGainDocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class LossAndGainDocData extends AbstractDocData<LossAndGainDocPO> {

    private String docName = "BSBYD";

    public LossAndGainDocData() {
        super(LossAndGainDocPO.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
