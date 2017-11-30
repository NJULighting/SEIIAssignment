
import nju.lighting.data.promotiondata.PromotionData;
import nju.lighting.po.promotion.PromotionPO;
import nju.lighting.po.promotion.PromotionPackageItemPO;
import org.junit.Test;
import shared.CustomerGrade;
import shared.PromotionType;
import shared.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created on 2017/11/30.
 * Description:
 *
 * @author iznauy
 */
public class PromotionDataTest {

    private PromotionData promotionData;

    public PromotionDataTest() throws RemoteException {
        promotionData = new PromotionData();
    }

    @Test
    public void insert() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 10, 10);
        Date beginTime = calendar.getTime();
        Date createTime = calendar.getTime();
        calendar.set(2017, 12, 10);
        Date endTime = calendar.getTime();
        Date voucherEndTime = calendar.getTime();
        List<PromotionPackageItemPO> itemPOS = new ArrayList<>();
        PromotionPackageItemPO promotionPackageItemPO1 = new PromotionPackageItemPO("1-1-00001");
        PromotionPackageItemPO promotionPackageItemPO2 = new PromotionPackageItemPO("4-00001");
        itemPOS.add(promotionPackageItemPO1);
        itemPOS.add(promotionPackageItemPO2);
        PromotionPO promotionPO1 = new PromotionPO("光棍节大促销", PromotionType.CustomerOriented, beginTime, endTime, createTime,
                CustomerGrade.FIVE, 1000, null, 0.1, 10000, voucherEndTime);
        PromotionPO promotionPO2 = new PromotionPO("老板带着小姨子跑了，清仓大处理", PromotionType.Combo,
                beginTime, endTime, createTime, CustomerGrade.FIVE, 1000, itemPOS, 0.1, 10000, voucherEndTime);
        ResultMessage resultMessage1 = promotionData.insert(promotionPO1).t;
        ResultMessage resultMessage2 = promotionData.insert(promotionPO2).t;
        assertEquals(ResultMessage.SUCCESS, resultMessage1);
        assertEquals(ResultMessage.SUCCESS, resultMessage2);
    }

    @Test
    public void getPromotionList() throws Exception {
        List<PromotionPO> promotionPOS;
        promotionPOS = promotionData.getPromotionList();
        for(PromotionPO promotionPO: promotionPOS) {
            System.out.println(promotionPO);
        }
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void getPromotionById() throws Exception {
        PromotionPO promotionPO = promotionData.getPromotionById(11);
        System.out.println(promotionPO);
    }

}