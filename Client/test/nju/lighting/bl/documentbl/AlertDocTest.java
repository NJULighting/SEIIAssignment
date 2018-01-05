package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.alertdoc.AlertDoc;
import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import org.junit.Test;
import shared.DocType;

import javax.naming.NamingException;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2018/1/5.
 * Description:
 * @author Liao
 */
public class AlertDocTest {
    private DocManager manager = DocManager.INSTANCE;
    private DocDataService dataService;

    public AlertDocTest() throws NamingException {
        dataService = DataFactory.getDataBase(DocDataService.class);
    }

    @Test
    public void expireDocTest() throws Exception {
        DocFactory docFactory = DocFactory.INSTANT;
        List<Doc> docList = DataServiceFunction.findByToList(DocType.ALERT, dataService::findByType, docFactory::poToDoc);
        boolean res = docList.stream().allMatch(alert -> ((AlertDoc) alert).expireAlert().success());

        assertTrue(res);
    }
}
