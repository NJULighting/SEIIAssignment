package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.accountiodoc.AccountIODoc;
import nju.lighting.po.doc.DocPO;
import shared.DocType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
class DocumentFactory {
    private Map<DocType, Function<DocPO, Doc>> docMap;

    DocumentFactory() {
        docMap = new HashMap<>();
        docMap.put(DocType.ACCOUNT_IN, AccountIODoc::new);
        docMap.put(DocType.ACCOUNT_OUT, AccountIODoc::new);
    }

    Doc createDoc(DocType type, DocPO po) {
        return docMap.get(type).apply(po);
    }
}
