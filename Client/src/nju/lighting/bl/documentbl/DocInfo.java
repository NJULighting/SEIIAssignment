package nju.lighting.bl.documentbl;


import nju.lighting.vo.doc.historydoc.HistoryDocVO;

import java.util.ArrayList;

/**
 * Created on 2017/11/7.
 * Description
 * @author 陈俊宇
 */

public interface DocInfo {
    ArrayList<HistoryDocVO> findUnCheckedDoc();
}


