package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.RevenueAndExpenditureVO;
import nju.lighting.vo.viewtables.SaleRecordItemVO;
import shared.DocType;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.SaleRecordFilter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/7.
 * Description:
 * @author Liao
 */
public class DocController implements DocBLService {
    @Override
    public DocVO createDoc(DocType type) {
        return null;
    }

    @Override
    public ResultMessage commitDoc(DocVO doc) {
        return null;
    }

    @Override
    public ArrayList<HistoryDocVO> findDocuments(DocumentFilter filter) {
        return null;
    }

    @Override
    public ArrayList<SaleRecordItemVO> findSaleRecords(SaleRecordFilter filter) {
        return null;
    }

    @Override
    public ArrayList<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) {
        return null;
    }

    @Override
    public RevenueAndExpenditureVO findRevenueAndExpenditure(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public ResultMessage redFlush(DocVO docVO) {
        return null;
    }

    @Override
    public DocVO redFlushAndCopy(DocVO target) {
        return null;
    }
}
