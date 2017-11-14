package nju.lighting.bl.documentbl;

import java.util.ArrayList;

/**
 * Created on 2017/11/14.
 * Description
 *
 * @author 陈俊宇
 */
public class MockDocManage  implements DocInfo{
    @Override
    public ArrayList<HistoryDoc> findUnCheckedDoc() {
        ArrayList<HistoryDoc> historyDocs = new ArrayList<>();

        return  historyDocs;
    }
}
