import nju.lighting.data.docdata.DocDataController;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import org.junit.Test;
import shared.*;

import java.rmi.RemoteException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
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
        Result<String> tuple = docDataController.commitDoc(alertDocPO);
        String id = tuple.getValue();
        ResultMessage resultMessage1 = tuple.getResultMessage();
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
        Date start = new Date(Instant.now().minus(Duration.ofDays(1)).toEpochMilli());
        Date end = new Date(Instant.now().plus(Duration.ofDays(1)).toEpochMilli());
        System.out.println(start);
        System.out.println(end);
        List<DocPO> poList = docDataController.findByTime(start, end);
        poList.forEach(System.out::println);
        assertNotEquals(0, poList.size());
    }

    @Test
    public void findByTimeAndType() throws Exception {
    }

    @Test //发送到学生邮箱中
    public void mail() throws Exception {
        System.out.println(System.getProperty("user.dir"));
        String id = "161250220";
        String header = "我系古天乐";
        String content = "我系渣渣辉";
        docDataController.sentMail(id, header, content);
    }
}