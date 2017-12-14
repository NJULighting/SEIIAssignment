package nju.lighting.bl.approvalbl;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import shared.DocState;

import javax.naming.NamingException;
import java.util.List;

/**
 * Created on 2017/11/13.
 * Description 审批单据的领域模型对象
 * @author 陈俊宇
 */
public enum ApprovalManager {
    INSTANCE;

    private DocDataService dataService;

    ApprovalManager(){
        try {
            dataService = DataFactory.getDataBase(DocDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    List<DocVO> getDocumentList() {
        return null; // TODO: 2017/12/13 complete it
    }
}
