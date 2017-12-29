package nju.lighting.vo.doc.costdoc;

import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.costdoc.CostDocItemPO;
import nju.lighting.po.doc.costdoc.CostDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.account.AccountVO;
import shared.DocType;

import java.util.Date;
import java.util.List;

/**
 * Created on 2017/10/21.
 * Description:
 * @author Liao
 */
public class CostDocVO extends DocVO {
    private AccountVO account; // 用户最后在现金费用单中选择的账户
    private List<CostDocItemVO> itemList;
    private double total;

    /**
     * Constructor for bl
     */
    public CostDocVO(Date time, String creatorId, String docId
            , AccountVO account, List<CostDocItemVO> itemList) {
        super(time, creatorId, docId, DocType.COST);
        this.account = account;
        this.itemList = itemList;
        total = calculateTotal();
    }

    /**
     * Constructor for pre
     */
    public CostDocVO(Date time, String creatorId, AccountVO account,
                     List<CostDocItemVO> itemList) {
        super(time, DocType.COST, creatorId);
        this.account = account;
        this.itemList = itemList;
        this.total = calculateTotal();
    }

    public AccountVO getAccount() {
        return account;
    }

    public List<CostDocItemVO> getItemList() {
        return itemList;
    }

    public double getTotal() {
        return total;
    }

    private double calculateTotal() {
        return itemList.stream().mapToDouble(CostDocItemVO::getAmount).sum();
    }

    @Override
    public DocPO toPO() {
        List<CostDocItemPO> poList = CollectionTransformer.toList(itemList, CostDocItemVO::toPO);
        return new CostDocPO(getType(), getCreatorId(), getTime(), account.getId(), poList, total);
    }
}
