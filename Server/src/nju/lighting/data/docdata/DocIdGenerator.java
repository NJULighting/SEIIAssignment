package nju.lighting.data.docdata;

import nju.lighting.data.utils.HibernateUtils;
import nju.lighting.po.doc.accountiodoc.AccountIODocPO;
import nju.lighting.po.doc.alertdoc.AlertDocItemPO;
import nju.lighting.po.doc.alertdoc.AlertDocPO;
import nju.lighting.po.doc.costdoc.CostDocPO;
import nju.lighting.po.doc.giftdoc.GiftDocPO;
import nju.lighting.po.doc.lossandgaindoc.LossAndGainDocPO;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
import nju.lighting.po.doc.salesdoc.SalesReturnDocPO;
import nju.lighting.po.doc.stockdoc.StockDocPO;
import nju.lighting.po.doc.stockdoc.StockReturnDocPO;
import org.hibernate.Session;
import shared.DocType;
import shared.TwoTuple;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created on 2017/11/28.
 * Description:
 *
 * @author iznauy
 */
public class DocIdGenerator {

    protected static HashMap<DocType, TwoTuple<String, String>> typeToName = new HashMap<>();

    static {
        typeToName.put(DocType.ALERT, new TwoTuple<>("BJD", AlertDocPO.class.getName()));
        typeToName.put(DocType.LOSS_AND_GAIN, new TwoTuple<>("BSBYD", LossAndGainDocPO.class.getName()));
        typeToName.put(DocType.ACCOUNT_INOUT, new TwoTuple<>("ZHD", AccountIODocPO.class.getName()));
        typeToName.put(DocType.SALES_RETURN, new TwoTuple<>("XSTHD", SalesReturnDocPO.class.getName()));
        typeToName.put(DocType.SALES, new TwoTuple<>("XSD", SalesDocPO.class.getName()));
        typeToName.put(DocType.COST, new TwoTuple<>("XJFYD", CostDocPO.class.getName()));
        typeToName.put(DocType.GIFT, new TwoTuple<>("LPD", GiftDocPO.class.getName()));
        typeToName.put(DocType.STOCK, new TwoTuple<>("JHD", StockDocPO.class.getName()));
        typeToName.put(DocType.STOCK_RETURN, new TwoTuple<>("JHTHD", StockReturnDocPO.class.getName()));
    }

    private DocType docType;

    public DocIdGenerator(DocType docType) {
        this.docType = docType;
    }

    public String generateId() {
        TwoTuple<String, String> twoTuple = typeToName.get(docType);
        String id = twoTuple.t;
        Date now = new Date();
        SimpleDateFormat naiveFormat = new SimpleDateFormat("yyyyMMd");
        id += "-" + naiveFormat.format(now) + "-";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date begin = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        Date end = calendar.getTime();
        DocOperation docOperation = new DocOperation();
        int count = docOperation.countByTypeAndDate(begin, end, twoTuple.r) + 1;
        String countString = format(count);
        id += countString;
        System.out.println("生成的ID：————————> " + id);
        return id;
    }

    private static String format(int count) {
        if (count < 10)
            return "0000" + count;
        else if (count < 100)
            return "000" + count;
        else if (count < 1000)
            return "00" + count;
        else if (count < 10000)
            return "0" + count;
        else if (count < 100000)
            return "" + count;
        else
            return "99999";
    }

}
