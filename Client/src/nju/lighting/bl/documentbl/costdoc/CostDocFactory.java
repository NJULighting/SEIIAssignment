package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.accountbl.AccountInfo;
import nju.lighting.bl.accountbl.AccountInfoImpl;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.DocFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.costdoc.CostDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import nju.lighting.vo.doc.costdoc.CostDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;

import java.util.List;

/**
 * Created on 2017/12/17.
 * Description:
 * @author Liao
 */
public class CostDocFactory implements DocFactory {
    @Override
    public Doc createDocForApproval(HistoryDocVO historyDocVO) {
        return null;
    }

    @Override
    public DocVO poToDocVO(DocPO po) {
        AccountInfo accountInfo = new AccountInfoImpl();
        CostDocPO costDocPO = (CostDocPO) po;

        // Item list
        List<CostDocItemVO> itemVOList = VPOTransformer.toVPOList(costDocPO.getItemList(),
                itemPO -> new CostDocItemVO(itemPO.getId(), itemPO.getType(), itemPO.getAmount(), itemPO.getComment()));

        return new CostDocVO(po.getCreateTime(), po.getUserId(), po.getId(),
                accountInfo.getAccountByID(costDocPO.getAccountID()), itemVOList);
    }
}
