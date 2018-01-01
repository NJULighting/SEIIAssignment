package nju.lighting.bl.documentbl;

import nju.lighting.bl.commoditybl.CommodityBLService_Stub;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.doc.salesdoc.SalesDocItemVO;
import nju.lighting.vo.viewtables.BusinessConditionTableVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/12/30.
 * Description
 *
 * @author 陈俊宇
 */
public class DocBLService_Stub implements DocBLService {
    @Override
    public TwoTuple<ResultMessage, String> commitDoc(DocVO doc) {
        return null;
    }

    @Override
    public List<HistoryDocVO> findDocuments(DocumentFilter filter) {
        return null;
    }

    @Override
    public List<SalesDetailItemVO> findSaleRecords(DocumentFilter filter) {
        SalesDetailItemVO vo1=new SalesDetailItemVO(new Date(),"金坷垃","a",12,10,120);
        SalesDetailItemVO vo2=new SalesDetailItemVO(new Date(),"金坷垃","b",1,10,10);
        SalesDetailItemVO vo3=new SalesDetailItemVO(new Date(),"金坷垃","d",12,10,120);
        SalesDetailItemVO vo4=new SalesDetailItemVO(new Date(),"金坷垃","h",11,10,110);
        SalesDetailItemVO vo5=new SalesDetailItemVO(new Date(),"金坷垃","e",3,10,30);
        SalesDetailItemVO vo6=new SalesDetailItemVO(new Date(),"金坷垃","n",2,10,20);
        SalesDetailItemVO vo7=new SalesDetailItemVO(new Date(),"金坷垃","j",5,10,50);

        ArrayList<SalesDetailItemVO> res=new ArrayList();
        res.add(vo1);
        res.add(vo2);
        res.add(vo3);
        res.add(vo4);
        res.add(vo5);
        res.add(vo6);
        res.add(vo7);

        System.out.println(filter.getCustomerId());
        if (filter.getCustomerId()!=null&&filter.getCustomerId().length()!=0)
            return res.subList(2,6);

        return res;
    }

    @Override
    public List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) {
        return null;
    }

    @Override
    public BusinessConditionTableVO findRevenueAndExpenditure(Date startDate, Date endDate) {
        if (startDate.equals(endDate))
            return new BusinessConditionTableVO(111, 112, 113,
                    114, 115, 116, 117,
                    118, 119);
        else
            return new BusinessConditionTableVO(160,213,124,
                    25,624,23,234,
                    643,123);
    }

    @Override
    public ResultMessage redFlush(DocVO docVO) {
        return null;
    }

    @Override
    public DocVO redFlushAndCopy(DocVO target) {
        return null;
    }

    @Override
    public UserVO getCreatorInfo(String creatorID) {
        return null;
    }
}
