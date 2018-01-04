package nju.lighting.bl.documentbl;

import nju.lighting.bl.userbl.LoginTestHelper;
import nju.lighting.bl.userbl.UserInfo;
import nju.lighting.bl.userbl.UserInfoImpl;
import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.bl.utils.DataServiceBiFunction;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.DocType;

import javax.naming.NamingException;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/12/28.
 * Description:
 * @author Liao
 */
public class ApproveTestHelper {

    private static DocDataService dataService;
    private static DocFactory docFactory = DocFactory.INSTANT;

    static {
        try {
            LoginTestHelper.loginAuthorizedUser();
            dataService = DataFactory.getDataBase(DocDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static List<HistoryDocVO> getDocsForApproving(DocType docType) {
        return getDocs(getDocOfType(docType), DocState.APPROVAL);
    }

    public static List<HistoryDocVO> getDocsForApproving() {
        return getDocs(getAll(), DocState.APPROVAL);
    }

    public static List<HistoryDocVO> getDocsForSaving(DocType docType) {
        return getDocs(getDocOfType(docType), DocState.UN_CHECKED);
    }

    private static List<HistoryDocVO> getDocs(List<DocVO> voList, DocState state) {
        UserInfo userInfo = new UserInfoImpl();

        return CollectionTransformer.toList(voList, vo -> new HistoryDocVO(userInfo.getUserVOByID(vo.getCreatorId()), vo,
                "test", state, new Date(),
                userInfo.getUserVOByID(userInfo.getIDOfSignedUser())));
    }

    private static List<DocVO> getAll() {
        return DataServiceFunction
                .findAndFilterToList(DocState.UN_CHECKED, dataService::findByState,
                        po -> docFactory.poToDoc(po).toVO(), vo -> vo.getType() != DocType.ALERT);
    }

    private static List<DocVO> getDocOfType(DocType type) {
        return DataServiceBiFunction
                .findToList(type, DocState.UN_CHECKED,
                        dataService::findByTypeAndState, po -> docFactory.poToDoc(po).toVO());
    }
}
