package nju.lighting.data.documentdata;

import nju.lighting.po.doc.giftdoc.GiftDocPO;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class GiftDocData extends AbstractDocData<GiftDocPO> {

    private String docName = "LPD";

    public GiftDocData() {
        super(GiftDocData.class);
    }

    @Override
    protected String getDocName() {
        return docName;
    }
}
