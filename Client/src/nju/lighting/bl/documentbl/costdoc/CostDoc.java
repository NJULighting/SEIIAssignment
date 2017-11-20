package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.accountbl.AccountInfo;
import nju.lighting.bl.accountbl.MockAccountInfo;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.account.AccountVO;
import nju.lighting.vo.doc.costdoc.CostDocItemVO;
import nju.lighting.vo.doc.costdoc.CostDocVO;
import nju.lighting.vo.DocVO;
import shared.DocType;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/7.
 * Description: 负责创建初始的现金费用单VO
 * @author Liao
 */
public class CostDoc extends Doc {

    private ArrayList<CostDocItem> costDocItems;
    private String chosenAccount;
    private double totalAmount;

    public CostDoc(String id, String userId, Date time,ArrayList<CostDocItem> costDocItems) {
        super(id, DocType.COST, userId, time);
        this.costDocItems = costDocItems;
    }

    public CostDoc(CostDocVO vo) {
        super(vo.getDocId(), DocType.COST, vo.getCreatorId(), vo.getTime());
        // Create cost items list
        costDocItems = new ArrayList<>();
        for (CostDocItemVO item : vo.getItemList()) {
            costDocItems.add(new CostDocItem(item));
        }
        // Create chosen account
        AccountVO chosenAccountVO = vo.getAccount();
        chosenAccount = chosenAccountVO.getName();
        // Total amount
        totalAmount = vo.getTotal();
    }

    /**
     * 修改响应账户金额
     */
    @Override
    public void approve() {
        AccountInfo accountInfo = new MockAccountInfo();
        accountInfo.updateAmount(chosenAccount, totalAmount);
    }

    @Override
    public ResultMessage reject() {
        return null;
    }

    @Override
    public ResultMessage modify() {
        return null;
    }

    @Override
    public DocVO createVO() {
        return null;
    }

    @Override
    public DocPO createPO() {
        return null;
    }
}
