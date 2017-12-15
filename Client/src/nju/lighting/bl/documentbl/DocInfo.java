package nju.lighting.bl.documentbl;


import nju.lighting.vo.DocVO;

import java.util.List;

/**
 * Created on 2017/11/7.
 * Description
 * @author 陈俊宇
 */

public interface DocInfo {
    List<DocVO> findUnCheckedDoc();
}


