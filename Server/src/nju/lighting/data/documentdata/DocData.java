package nju.lighting.data.documentdata;

import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import shared.*;

import java.rmi.RemoteException;
import java.util.*;

/**
 * Created on 2017/11/27.
 * Description: 用于DocData模块请求的分发
 *
 * @author iznauy
 */
public class DocData implements DocDataService {

    private AccountIODocData accountIODocData;

    private AlertDocData alertDocData;

    private CostDocData costDocData;

    private GiftDocData giftDocData;

    private LossAndGainDocData lossAndGainDocData;

    private SaleDocData saleDocData;

    private SaleReturnDocData saleReturnDocData;

    private StockReturnData stockReturnData;

    private StockDocData stockDocData;

    private HashMap<DocType, AbstractDocData> locateDataService;

    public DocData() {
        accountIODocData = new AccountIODocData();
        alertDocData = new AlertDocData();
        costDocData = new CostDocData();
        giftDocData = new GiftDocData();
        lossAndGainDocData = new LossAndGainDocData();
        saleDocData = new SaleDocData();
        saleReturnDocData = new SaleReturnDocData();
        stockDocData = new StockDocData();
        stockReturnData = new StockReturnData();
        locateDataService = new HashMap<>();
        locateDataService.put(DocType.ACCOUNT_INOUT, accountIODocData);
        locateDataService.put(DocType.ALERT, alertDocData);
        locateDataService.put(DocType.COST, costDocData);
        locateDataService.put(DocType.GIFT, giftDocData);
        locateDataService.put(DocType.SALES, saleDocData);
        locateDataService.put(DocType.SALES_RETURN, saleReturnDocData);
        locateDataService.put(DocType.STOCK, stockDocData);
        locateDataService.put(DocType.STOCK_RETURN, stockReturnData);
        locateDataService.put(DocType.LOSS_AND_GAIN, lossAndGainDocData);
    }

    private AbstractDocData getAbstractDocData(DocType docType) {
        return locateDataService.get(docType);
    }

    @Override
    public TwoTuple<ResultMessage, String> commitDoc(DocPO doc) throws RemoteException {
        AbstractDocData abstractDocData = getAbstractDocData(doc.getDocType());
        return abstractDocData.commitDoc(doc);
    }

    @Override
    public ResultMessage updateDoc(DocPO doc) throws RemoteException {
        AbstractDocData abstractDocData = getAbstractDocData(doc.getDocType());
        return abstractDocData.updateDoc(doc);
    }

    @Override
    public List<DocPO> findByUserId(String id) throws RemoteException {
        Collection<AbstractDocData> abstractDocDataList = locateDataService.values();
        List<DocPO> docPOS = new ArrayList<>();
        for (AbstractDocData abstractDocData: abstractDocDataList) {
            List<DocPO> docPOList = abstractDocData.findByUserId(id);
            docPOS.addAll(docPOS);
        }
        return docPOS;
    }

    @Override
    public List<DocPO> findByState(DocState docState) throws RemoteException {
        Collection<AbstractDocData> abstractDocDataList = locateDataService.values();
        List<DocPO> docPOS = new ArrayList<>();
        for (AbstractDocData abstractDocData: abstractDocDataList) {
            List<DocPO> docPOList = abstractDocData.findByState(docState);
            docPOS.addAll(docPOS);
        }
        return docPOS;
    }

    @Override
    public List<DocPO> findByType(DocType type) throws RemoteException {
        AbstractDocData abstractDocData = getAbstractDocData(type);
        return abstractDocData.getAll();
    }

    @Override
    public List<DocPO> findByTime(Date from, Date to) throws RemoteException {
        Collection<AbstractDocData> abstractDocDataList = locateDataService.values();
        List<DocPO> docPOS = new ArrayList<>();
        for (AbstractDocData abstractDocData: abstractDocDataList) {
            List<DocPO> docPOList = abstractDocData.getByTime(from, to);
            docPOS.addAll(docPOS);
        }
        return docPOS;
    }

    @Override
    public List<DocPO> findByTimeAndType(Date from, Date to, DocType type) throws RemoteException {
        AbstractDocData abstractDocData = getAbstractDocData(type);
        List<DocPO> original = abstractDocData.getAll();
        List<DocPO> result = new ArrayList<>();
        for (DocPO doc: original) {
            Date time = doc.getTime();
            if (time.compareTo(from) >= 0 && time.compareTo(to) < 0)
                result.add(doc);
        }
        return result;
    }
}
