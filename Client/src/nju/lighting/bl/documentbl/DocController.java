package nju.lighting.bl.documentbl;

import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.RevenueAndExpenditureVO;
import nju.lighting.vo.viewtables.SaleRecordItemVO;
import shared.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2017/11/7.
 * Description:
 * @author Liao
 */
public class DocController implements DocBLService {
    @Override
    public DocVO createDoc(DocType type) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage commitDoc(DocVO doc) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<SaleRecordItemVO> findSaleRecords(SaleRecordFilter filter) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) throws RemoteException {
        return null;
    }

    @Override
    public RevenueAndExpenditureVO findRevenueAndExpenditure(Date startDate, Date endDate) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage redFlush(DocVO docVO) throws RemoteException {
        return null;
    }

    @Override
    public DocVO redFlushAndCopy(DocVO target) throws RemoteException {
        return null;
    }
}
