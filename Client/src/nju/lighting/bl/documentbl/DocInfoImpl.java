package nju.lighting.bl.documentbl;

import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import shared.DocState;

import javax.naming.NamingException;
import java.util.List;

/**
 * Created on 2017/12/13.
 * Description:
 * @author Liao
 */
public class DocInfoImpl implements DocInfo {
    private DocDataService dataService;

    public DocInfoImpl() {
        try {
            dataService = DataFactory.getDataBase(DocDataService.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DocVO> findUnCheckedDoc() {
        DocumentFactory documentFactory = new DocumentFactory();
        return DataServiceFunction.findByToList(DocState.UN_CHECKED, dataService::findByState,
                po -> documentFactory.createDoc(po.getDocType(), po).toVO());
    }
}