package nju.lighting.bl.documentbl.costdoc;

import nju.lighting.bl.accountbl.AccountInfo;
import nju.lighting.bl.accountbl.MockAccountInfo;
import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;
import nju.lighting.vo.DocVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/11/7.
 * Description: 负责创建初始的现金费用单VO
 * @author Liao
 */
public class CostDoc extends Doc {

    private List<CostDocItem> costDocItems;
    private String chosenAccount;
    private double totalAmount;

    public CostDoc(DocPO po) {
        super(po);
    }

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
    public DocVO toVO() {
        return null;
    }

    @Override
    public DocPO toPO() {
        return null;
    }

    @Override
    public boolean containsCustomer(String customerId) {
        return false;
    }

    @Override
    public boolean containsCommodity(String commodityName) {
        return false;
    }

    @Override
    public boolean containsRepository(String repository) {
        return false;
    }

}
