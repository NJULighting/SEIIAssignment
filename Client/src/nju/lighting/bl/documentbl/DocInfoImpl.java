package nju.lighting.bl.documentbl;

import nju.lighting.bl.utils.DataServiceFunction;
import nju.lighting.dataservice.DataFactory;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocState;
import shared.ResultMessage;

import javax.naming.NamingException;
import java.rmi.RemoteException;
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
        DocFactory docFactory = new DocFactory();
        return DataServiceFunction.findByToList(DocState.UN_CHECKED,
                dataService::findByState, po -> docFactory.poToDoc(po).toVO());
    }

    @Override
    public ResultMessage approve(HistoryDocVO vo) {
        return null;
    }

    @Override
    public ResultMessage approveAll(List<HistoryDocVO> voList) {
        return null;
    }

    @Override
    public ResultMessage reject(HistoryDocVO vo) {
        DocFactory docFactory = new DocFactory();
        Doc doc = docFactory.historyDocVOToDoc(vo);
        doc.reject();
        try {
            return dataService.updateDoc(doc.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.NETWORK_FAIL;
        }
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) {
        return null;
    }

    @Override
    public ResultMessage saveAndApprove(HistoryDocVO vo) {
        return null;
    }


}