package nju.lighting.blservice.approvalblservice;


import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/10/21.
 * Description
 * @author 陈俊宇
 */
public interface ApprovalBLService {
    ArrayList<HistoryDocVO> getDocumentList() ;

    ResultMessage approve(HistoryDocVO vo) ;

    ResultMessage approveAll(ArrayList<HistoryDocVO> voList) ;

    ResultMessage reject(HistoryDocVO vo) ;

    ResultMessage save(HistoryDocVO vo) ;

    ResultMessage saveAndApprove(HistoryDocVO vo) ;
}
