package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;

import java.util.List;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
class AccountDocItemList {
    private ItemList<AccountDocItem> itemList;

    AccountDocItemList() {
        itemList = new ItemList<>();
    }


    void add(AccountTransferItemVO docItem) {
        itemList.add(docItem, AccountDocItem::new);
    }

    void add(AccountTransferItemPO docItem) {
        itemList.add(docItem, AccountDocItem::new);
    }

    List<AccountTransferItemPO> toPO(String docId) {
        return itemList.toPO(item -> item.toPO(docId));
    }

    List<AccountTransferItemVO> toVO() {
        return itemList.toVO(AccountDocItem::toVO);
    }

    void redFlush() {
        itemList.redFlush();
    }
}
