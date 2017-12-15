package nju.lighting.blservice.approvalblservice;


import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

import java.util.List;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public interface ApprovalBLService {
    List<DocVO> getDocumentList() ;

    ResultMessage approve(HistoryDocVO vo) ;

    ResultMessage approveAll(List<HistoryDocVO> voList) ;

    ResultMessage reject(HistoryDocVO vo) ;

    ResultMessage save(HistoryDocVO vo) ;

    ResultMessage saveAndApprove(HistoryDocVO vo) ;
}
