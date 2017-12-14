package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
class AccountDocItemList {
    private List<AccountDocItem> itemList;

    public AccountDocItemList(List<AccountTransferItemPO> itemPOList) {
        itemList = itemPOList.stream().map(AccountDocItem::new).collect(Collectors.toList());
    }

    List<AccountTransferItemPO> toPO(String docId) {
        return itemList.stream().map(docItem -> docItem.toPO(docId)).collect(Collectors.toList());
    }
}
