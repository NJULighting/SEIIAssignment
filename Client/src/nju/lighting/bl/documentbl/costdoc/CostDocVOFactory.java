package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.accountbl.AccountInfo;
import nju.lighting.bl.accountbl.AccountInfoImpl;
import nju.lighting.bl.documentbl.DocVOFactory;
import nju.lighting.bl.utils.VPOTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.costdoc.CostDocPO;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import nju.lighting.vo.doc.costdoc.CostDocVO;

import java.util.List;

/**
 * Created on 2017/12/17.
 * Description:
 * @author Liao
 */
public class CostDocVOFactory implements DocVOFactory {

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
