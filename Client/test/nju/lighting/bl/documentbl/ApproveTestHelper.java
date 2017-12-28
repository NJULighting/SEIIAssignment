package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.bl.utils.DataServiceBiFunction;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.bl.utils.ListTransformer;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.DocType;

import javax.naming.NamingException;
import javax.print.DocFlavor;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/12/28.
 * Description:
 * @author Liao
 */
public class ApproveTestHelper {

    private static DocDataService dataService;

    static {
        try {
            dataService = DataFactory.getDataBase(DocDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static List<HistoryDocVO> getDocsForApproving(DocType docType) {
        DocFactory docFactory = DocFactory.INSTANT;
        UserInfo userInfo = new UserInfoImpl();
        List<DocVO> voList = DataServiceBiFunction
                .findToList(docType, DocState.UN_CHECKED,
                        dataService::findByTypeAndState, po -> docFactory.poToDoc(po).toVO());

        return ListTransformer.toList(voList, vo -> new HistoryDocVO(userInfo.getUserVOByID(vo.getCreatorId()), vo,
                "test", DocState.APPROVAL, new Date(),
                userInfo.getUserVOByID(userInfo.getIDOfSignedUser())));
    }
}
