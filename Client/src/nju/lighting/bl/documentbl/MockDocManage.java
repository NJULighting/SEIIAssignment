package nju.lighting.bl.documentbl;

import nju.lighting.vo.DocVO;

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
}
