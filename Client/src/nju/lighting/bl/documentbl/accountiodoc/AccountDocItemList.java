package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.presentation.documentui.AccountTransferItem;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
class AccountDocItemList {
    private List<AccountDocItem> itemList;

    AccountDocItemList(List<AccountTransferItemPO> itemPOList) {
        itemList = itemPOList.stream().map(AccountDocItem::new).collect(Collectors.toList());
    }

    AccountDocItemList() {
        itemList = new ArrayList<>();
    }

    void addAll(Collection<AccountTransferItemVO> vos) {
        itemList.addAll(vos.stream().map(AccountDocItem::new).collect(Collectors.toList()));
    }

    List<AccountTransferItemPO> toPO(String docId) {
        return itemList.stream().map(docItem -> docItem.toPO(docId)).collect(Collectors.toList());
    }
}
