package nju.lighting.bl.documentbl;

import nju.lighting.bl.approvalbl.ApprovalBLService_Stub;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;
import nju.lighting.blservice.documentblservice.DocBLService;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.UserVO;
import nju.lighting.vo.doc.alertdoc.AlertDocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessConditionTableVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import shared.DocState;
import shared.DocumentFilter;
import shared.ResultMessage;
import shared.TwoTuple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2017/12/30.
 * Description
 * @author 陈俊宇
 */
public class DocBLService_Stub implements DocBLService {
    @Override
    public TwoTuple<ResultMessage, String> commitDoc(DocVO doc) {
        return
                new TwoTuple<>(ResultMessage.SUCCESS, "");
    }

    @Override
    public List<HistoryDocVO> findDocuments(DocumentFilter filter) {
        List<HistoryDocVO> list=new ArrayList<>();

        ApprovalBLService approvalBLService=new ApprovalBLService_Stub();
        list.add(new HistoryDocVO(null,approvalBLService.getDocumentList().get(0),
                null, DocState.UN_CHECKED,null,null));
        list.add(new HistoryDocVO(null,approvalBLService.getDocumentList().get(8),
                null,DocState.APPROVAL,new Date(),null));

        list.add(new HistoryDocVO(null,approvalBLService.getDocumentList().get(9),
                "-1s",DocState.DISAPPROVAL,new Date(),null));

        return list;
    }

    @Override
    public List<SalesDetailItemVO> findSaleRecords(DocumentFilter filter) {
        SalesDetailItemVO vo1 = new SalesDetailItemVO(new Date(), "金坷垃", "a", 12, 10, 120);
        SalesDetailItemVO vo2 = new SalesDetailItemVO(new Date(), "金坷垃", "b", 1, 10, 10);
        SalesDetailItemVO vo3 = new SalesDetailItemVO(new Date(), "金坷垃", "d", 12, 10, 120);
        SalesDetailItemVO vo4 = new SalesDetailItemVO(new Date(), "金坷垃", "h", 11, 10, 110);
        SalesDetailItemVO vo5 = new SalesDetailItemVO(new Date(), "金坷垃", "e", 3, 10, 30);
        SalesDetailItemVO vo6 = new SalesDetailItemVO(new Date(), "金坷垃", "n", 2, 10, 20);
        SalesDetailItemVO vo7 = new SalesDetailItemVO(new Date(), "金坷垃", "j", 5, 10, 50);

        ArrayList<SalesDetailItemVO> res = new ArrayList();
        res.add(vo1);
        res.add(vo2);
        res.add(vo3);
        res.add(vo4);
        res.add(vo5);
        res.add(vo6);
        res.add(vo7);

        System.out.println(filter.getCustomerId());
        if (filter.getCustomerId() != null && filter.getCustomerId().length() != 0)
            return res.subList(2, 6);

        return res;
    }

    @Override
    public List<BusinessHistoryItemVO> findBusinessHistory(DocumentFilter filter) {

        return new ApprovalBLService_Stub().getDocumentList().stream()
                .map(x-> new BusinessHistoryItemVO(x.getTime(),x.getType(),x,null,
                        x.getCreatorId(),null))
                .collect(Collectors.toList());
    }

    @Override
    public BusinessConditionTableVO findRevenueAndExpenditure(Date startDate, Date endDate) {
        if (startDate.equals(endDate))
            return new BusinessConditionTableVO(111, 112, 113,
                    114, 115, 116, 117,
                    118, 119);
        else
            return new BusinessConditionTableVO(160, 213, 124,
                    25, 624, 23, 234,
                    643, 123);
    }

    @Override
    public ResultMessage expireAlertDoc(AlertDocVO alertDocVO) {
        return null;
    }

    @Override
    public ResultMessage redFlush(DocVO docVO) {
        return null;
    }

    @Override
    public UserVO getCreatorInfo(String creatorID) {
        return null;
    }
}
