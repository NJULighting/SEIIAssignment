import nju.lighting.data.docdata.DocDataController;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import org.junit.Test;
import shared.DocState;
import shared.DocType;
import shared.ResultMessage;
import shared.TwoTuple;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/30.
 * Description:
 *
 * @author iznauy
 */
public class DocDataControllerTest {

    private DocDataController docDataController;

    public DocDataControllerTest() throws RemoteException {
        docDataController = new DocDataController();
    }

    @Test
    public void commitDoc() throws Exception {
        AlertDocItemPO alertDocItemPO1 = new AlertDocItemPO("1-4-0001", 5);
        AlertDocItemPO alertDocItemPO2 = new AlertDocItemPO("4-0001", 5);
        List<AlertDocItemPO> alertDocItemPOS = new ArrayList<>();
        alertDocItemPOS.add(alertDocItemPO1);
        alertDocItemPOS.add(alertDocItemPO2);
        AlertDocPO alertDocPO = new AlertDocPO(DocType.ALERT, "161250220", new Date(), null,
                false, false, alertDocItemPOS);
        TwoTuple<ResultMessage, String> tuple = docDataController.commitDoc(alertDocPO);
        String id = tuple.r;
        ResultMessage resultMessage1 = tuple.t;
        System.out.println(id);
        assertEquals(ResultMessage.SUCCESS, resultMessage1);
    }

    @Test
    public void updateDoc() throws Exception {
        AlertDocItemPO alertDocItemPO1 = new AlertDocItemPO(1, "BJD-20171130-00001","1-4-0001", 3);
        AlertDocItemPO alertDocItemPO2 = new AlertDocItemPO(2, "BJD-20171130-00001","4-0001", 3);
        List<AlertDocItemPO> alertDocItemPOS = new ArrayList<>();
        alertDocItemPOS.add(alertDocItemPO1);
        alertDocItemPOS.add(alertDocItemPO2);
        AlertDocPO alertDocPO = new AlertDocPO("BJD-20171130-00001", DocType.ALERT, "161250220", new Date(), "WQNMLGB!明天不要来上班了",
                false, false, alertDocItemPOS);
        alertDocPO.setState(DocState.DISAPPROVAL);
        ResultMessage resultMessage = docDataController.updateDoc(alertDocPO);
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }

    @Test
    public void findByUserId() throws Exception {
        List<DocPO> docPOS = docDataController.findByUserId("161250220");
        for (DocPO docPO: docPOS) {
            System.out.println(docPOS);
        }
    }

    @Test
    public void findByState() throws Exception {
        List<DocPO> docPOS = docDataController.findByState(DocState.UN_CHECKED);
        for (DocPO docPO: docPOS) {
            System.out.println(docPOS);
        }
        System.out.println("——————————————我是华丽的分割线——————————————");
        docPOS = docDataController.findByState(DocState.DISAPPROVAL);
        for (DocPO docPO: docPOS) {
            System.out.println(docPOS);
        }
    }

    @Test
    public void findByType() throws Exception {
        List<DocPO> docPOS = docDataController.findByType(DocType.ALERT);
        for (DocPO docPO: docPOS) {
            System.out.println(docPOS);
        }
        System.out.println("——————————————我是华丽的分割线——————————————");
        docPOS = docDataController.findByType(DocType.COST);
        for (DocPO docPO: docPOS) {
            System.out.println(docPOS);
        }
    }

    @Test
    public void findByTime() throws Exception {
    }

    @Test
    public void findByTimeAndType() throws Exception {
    }

}