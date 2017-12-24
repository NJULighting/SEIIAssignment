package nju.lighting.bl.documentbl.accountiodoc;

import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.vo.doc.accountiodoc.AccountTransferItemVO;

import java.util.ArrayList;
import java.util.Collection;
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

    List<AccountTransferItemVO> toVO() {
        return itemList.stream().map(AccountDocItem::toVO).collect(Collectors.toList());
    }
}
