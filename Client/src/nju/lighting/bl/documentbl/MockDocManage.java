package nju.lighting.bl.documentbl;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/11/14.
 * Description
 * @author 陈俊宇
 */
@Deprecated
public class MockDocManage implements DocInfo {
    @Override
    public List<DocVO> findUnCheckedDoc() {
        ArrayList<DocVO> uncheckedDocs = new ArrayList<>();


        return uncheckedDocs;
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
        return null;
    }

    @Override
    public ResultMessage save(HistoryDocVO vo) {
        return null;
    }

    @Override
    public void triggerAlertDoc(String commodityId, int count) {

    }
}
