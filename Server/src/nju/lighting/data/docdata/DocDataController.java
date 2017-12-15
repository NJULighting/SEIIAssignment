package nju.lighting.data.docdata;

import nju.lighting.data.utils.CommonOperation;
import nju.lighting.data.utils.HibernateUtils;
import nju.lighting.dataservice.documentdataservice.DocDataService;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.po.doc.accountiodoc.AccountTransferItemPO;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import nju.lighting.po.doc.costdoc.CostDocItemPO;
import nju.lighting.po.doc.costdoc.CostDocPO;
import nju.lighting.po.doc.giftdoc.GiftDocPO;
import nju.lighting.po.doc.giftdoc.GiftItemPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainDocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainItemPO;
import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
import nju.lighting.po.doc.salesdoc.SalesReturnDocPO;
import nju.lighting.po.doc.stockdoc.StockDocItemPO;
import nju.lighting.po.doc.stockdoc.StockDocPO;
import nju.lighting.po.doc.stockdoc.StockReturnDocPO;
import org.hibernate.Session;
import shared.*;

import javax.print.Doc;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created on 2017/11/29.
 * Description:
 *
 * @author iznauy
 */
public class DocDataController extends UnicastRemoteObject implements DocDataService {


    protected static HashMap<DocType, TwoTuple<String, String>> typeToName = new HashMap<>();

    static {
        typeToName.put(DocType.ALERT, new TwoTuple<>(AlertDocPO.class.getName(), AlertDocItemPO.class.getName()));
        typeToName.put(DocType.LOSS_AND_GAIN, new TwoTuple<>(LossAndGainDocPO.class.getName(), LossAndGainItemPO.class.getName()));
        typeToName.put(DocType.ACCOUNT_IN, new TwoTuple<>(AccountIODocPO.class.getName(), AccountTransferItemPO.class.getName()));
        typeToName.put(DocType.ACCOUNT_OUT, new TwoTuple<>(AccountIODocPO.class.getName(), AccountTransferItemPO.class.getName()));
        typeToName.put(DocType.SALES_RETURN, new TwoTuple<>(SalesReturnDocPO.class.getName(), SalesDocItemPO.class.getName()));
        typeToName.put(DocType.SALES, new TwoTuple<>(SalesDocPO.class.getName(), SalesDocItemPO.class.getName()));
        typeToName.put(DocType.COST, new TwoTuple<>(CostDocPO.class.getName(), CostDocItemPO.class.getName()));
        typeToName.put(DocType.GIFT, new TwoTuple<>(GiftDocPO.class.getName(), GiftItemPO.class.getName()));
        typeToName.put(DocType.STOCK, new TwoTuple<>(StockDocPO.class.getName(), StockDocItemPO.class.getName()));
        typeToName.put(DocType.STOCK_RETURN, new TwoTuple<>(StockReturnDocPO.class.getName(), StockDocItemPO.class.getName()));
    }

    private DocOperation docOperation;

    public DocDataController() throws RemoteException {
        docOperation = new DocOperation();
    }

    @Override
    public TwoTuple<ResultMessage, String> commitDoc(DocPO doc) throws RemoteException {
        DocIdGenerator docIdGenerator = new DocIdGenerator(doc.getDocType());
        String id = docIdGenerator.generateId();
        doc.setId(id);
        ResultMessage resultMessage = docOperation.add(doc);
        if (resultMessage == ResultMessage.FAILURE)
            return new TwoTuple<>(ResultMessage.FAILURE, null);
        List<Object> items = doc.getItems();
        for (Object o: items) {
            ((Item) o).setDocId(id);
        }
        ResultMessage result = docOperation.addItemsList(items);
        return new TwoTuple<>(result, id);
    }

    @Override
    public ResultMessage updateDoc(DocPO doc) throws RemoteException {
        ResultMessage resultMessage = docOperation.update(doc);
        if (resultMessage == ResultMessage.FAILURE)
            return ResultMessage.FAILURE;
        return  docOperation.updateItemsList(doc.getItems());
    }

    @Override
    public List<DocPO> findByUserId(String id) throws RemoteException {
        List<DocPO> docPOS = new ArrayList<>();
        for (TwoTuple<String, String> twoTuple: typeToName.values()) {
            String className = twoTuple.t;
            String itemClassName = twoTuple.r;
            DocOperation docOperation = new DocOperation();
            List<DocPO> docPOList = docOperation.getByUserId(className, id);
            if (docPOList == null)
                continue;
            for (DocPO docPO: docPOList) {
                String docId = docPO.getId();
                List<Object> items = docOperation.getItemList(itemClassName, docId);
                docPO.setItems(items);
            }
            docPOS.addAll(docPOList);
        }
        return docPOS;
    }

    @Override
    public List<DocPO> findByState(DocState docState) throws RemoteException {
        List<DocPO> docPOS = new ArrayList<>();
        for (TwoTuple<String, String> twoTuple: typeToName.values()) {
            String className = twoTuple.t;
            String itemClassName = twoTuple.r;
            DocOperation docOperation = new DocOperation();
            List<DocPO> docPOList = docOperation.getByState(docState, className);
            for (DocPO docPO: docPOList) {
                String docId = docPO.getId();
                List<Object> items = docOperation.getItemList(itemClassName, docId);
                docPO.setItems(items);
            }
            docPOS.addAll(docPOList);
        }
        return docPOS;
    }

    @Override
    public List<DocPO> findByType(DocType type) throws RemoteException {
        TwoTuple<String, String> target = typeToName.get(type);
        String className = target.t;
        String itemClassName = target.r;
        DocOperation docOperation = new DocOperation();
        List<DocPO> docPOList = docOperation.getAll(className);
        for (DocPO docPO: docPOList) {
            String docId = docPO.getId();
            List<Object> items = docOperation.getItemList(itemClassName, docId);
            docPO.setItems(items);
        }
        return docPOList;
    }

    @Override
    public List<DocPO> findByTime(Date from, Date to) throws RemoteException {
        List<DocPO> docPOS = new ArrayList<>();
        for (TwoTuple<String, String> twoTuple: typeToName.values()) {
            String className = twoTuple.t;
            String itemClassName = twoTuple.r;
            DocOperation docOperation = new DocOperation();
            List<DocPO> docPOList = docOperation.getByDate(from, to, className);
            for (DocPO docPO: docPOList) {
                String docId = docPO.getId();
                List<Object> items = docOperation.getItemList(itemClassName, docId);
                docPO.setItems(items);
            }
            docPOS.addAll(docPOList);
        }
        return docPOS;
    }

    @Override
    public List<DocPO> findByTimeAndType(Date from, Date to, DocType type) throws RemoteException {
        List<DocPO> all = findByTime(from, to);
        List<DocPO> result = new ArrayList<>();
        for (DocPO docPO: all) {
            if (docPO.getDocType() == type)
                result.add(docPO);
        }
        return all;
    }


}
