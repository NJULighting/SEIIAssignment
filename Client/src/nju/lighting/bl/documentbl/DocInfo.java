package nju.lighting.bl.documentbl;


import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/11/7.
 * Description
 * @author 陈俊宇
 */

public interface DocInfo {
    List<DocVO> findUnCheckedDoc();

    ResultMessage approve(HistoryDocVO vo);

    ResultMessage approveAll(List<HistoryDocVO> voList);

    ResultMessage reject(HistoryDocVO vo);

    ResultMessage save(HistoryDocVO vo);

    void triggerAlertDoc(String commodityId, int count);
}


