package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.bl.documentbl.ItemList;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/12/14.
 * Description:
 * @author Liao
 */
class AccountDocItemList {
    private ItemList<AccountDocItem> itemList;
    private AccountIOType ioType;

    AccountDocItemList(AccountIOType ioType) {
        itemList = new ItemList<>();
        this.ioType = ioType;
    }


    void add(AccountTransferItemVO docItem) {
        itemList.add(docItem, item -> new AccountDocItem(docItem, ioType));
    }

    void add(AccountTransferItemPO docItem) {
        itemList.add(docItem, item -> new AccountDocItem(docItem, ioType));
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

    ResultMessage approve() {
        return itemList.approve();
    }
}
