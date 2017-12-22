package nju.lighting.bl.repositorybl;

import nju.lighting.blservice.repositoryblservice.RepositoryBLService;
import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableItemVO;
import nju.lighting.vo.repository.RepositoryTableVO;
import shared.RepositoryChangeType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/12/21.
 * Description
 *
 * @author 陈俊宇
 */
public class RepositoryBLService_Stub implements RepositoryBLService {
    @Override
    public List<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate) {

        RepositoryChangeVO vo1 = new RepositoryChangeVO("111", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());
        RepositoryChangeVO vo2 = new RepositoryChangeVO("112", "1", RepositoryChangeType.BE_RETURN, 15,
                1500, new Date());
        RepositoryChangeVO vo3 = new RepositoryChangeVO("113", "1", RepositoryChangeType.GAIN, 15,
                1500, new Date());
        RepositoryChangeVO vo4 = new RepositoryChangeVO("114", "1", RepositoryChangeType.LOSS, 15,
                1500, new Date());
        RepositoryChangeVO vo5 = new RepositoryChangeVO("115", "1", RepositoryChangeType.SELL, 15,
                1500, new Date());
        RepositoryChangeVO vo6 = new RepositoryChangeVO("116", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());
        RepositoryChangeVO vo7 = new RepositoryChangeVO("117", "1", RepositoryChangeType.SELL, 15,
                1500, new Date());
        RepositoryChangeVO vo8 = new RepositoryChangeVO("118", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());
        RepositoryChangeVO vo9 = new RepositoryChangeVO("119", "1", RepositoryChangeType.SELL, 15,
                1500, new Date());
        RepositoryChangeVO vo10 = new RepositoryChangeVO("120", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());
        RepositoryChangeVO vo11 = new RepositoryChangeVO("121", "1", RepositoryChangeType.SELL, 15,
                1500, new Date());
        RepositoryChangeVO vo12 = new RepositoryChangeVO("122", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());
        RepositoryChangeVO vo13 = new RepositoryChangeVO("123", "1", RepositoryChangeType.SELL, 15,
                1500, new Date());
        RepositoryChangeVO vo14 = new RepositoryChangeVO("1113", "1", RepositoryChangeType.SELL, 15,
                1500, new Date());
        RepositoryChangeVO vo15 = new RepositoryChangeVO("11112", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());
        RepositoryChangeVO vo16 = new RepositoryChangeVO("1111", "1", RepositoryChangeType.SELL, 15,
                1500, new Date());
        RepositoryChangeVO vo17 = new RepositoryChangeVO("1111", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());
        RepositoryChangeVO vo18 = new RepositoryChangeVO("11121", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());
        RepositoryChangeVO vo19 = new RepositoryChangeVO("111231", "1", RepositoryChangeType.BUY, 15,
                1500, new Date());

        List<RepositoryChangeVO> list1 = new ArrayList<>();
        list1.add(vo1);
        list1.add(vo2);
        list1.add(vo3);
        list1.add(vo4);
        list1.add(vo5);
        list1.add(vo6);
        list1.add(vo7);
        list1.add(vo8);
        list1.add(vo9);
        list1.add(vo10);
        list1.add(vo11);
        list1.add(vo12);
        list1.add(vo13);
        list1.add(vo14);
        list1.add(vo15);
        list1.add(vo16);
        list1.add(vo17);
        list1.add(vo18);
        list1.add(vo19);


        List<RepositoryChangeVO> list2 = new ArrayList<>(list1);
        list2.addAll(list1);
        if (endDate.equals(startDate))
            return list1;
        else return list2;

    }

    @Override
    public RepositoryTableVO getRepositoryTable() {
        RepositoryTableItemVO vo1=new RepositoryTableItemVO("1","001","gn",
                12,50.2,"asdasd","asdasd",new Date());
        RepositoryTableItemVO vo2=new RepositoryTableItemVO("1","001","fsg",
                12,50.2,"dsf","asdasd",new Date());
        RepositoryTableItemVO vo3=new RepositoryTableItemVO("1","001","sa",
                12,50.2,"cxzv","asdasd",new Date());
        RepositoryTableItemVO vo4=new RepositoryTableItemVO("1","001","nfbd",
                12,50.2,"wv","asdasd",new Date());
        RepositoryTableItemVO vo5=new RepositoryTableItemVO("1","001","sdt",
                12,50.2,"zgd","asdasd",new Date());

        RepositoryTableItemVO vo6=new RepositoryTableItemVO("1","001","brs",
                12,50.2,"rsb","asdasd",new Date());

        RepositoryTableItemVO vo7=new RepositoryTableItemVO("1","001","tbd",
                12,50.2,"asgt","asdasd",new Date());

        RepositoryTableItemVO vo8=new RepositoryTableItemVO("1","001","rbs",
                12,50.2,"erca","asdasd",new Date());

        RepositoryTableItemVO vo9=new RepositoryTableItemVO("1","001","sdt",
                12,50.2,"vts","asdasd",new Date());

        RepositoryTableItemVO vo10=new RepositoryTableItemVO("1","001","stdsd",
                12,50.2,"fas","asdasd",new Date());

        RepositoryTableItemVO vo11=new RepositoryTableItemVO("1","001","vrs",
                12,50.2," dad","asdasd",new Date());

        RepositoryTableItemVO vo12=new RepositoryTableItemVO("1","001","ave",
                12,50.2,"astf","asdasd",new Date());

        RepositoryTableItemVO vo13=new RepositoryTableItemVO("1","001","asf",
                12,50.2,"rsw","asdasd",new Date());

        RepositoryTableItemVO vo14=new RepositoryTableItemVO("1","001","vwr",
                12,50.2,"","asdasd",new Date());

        RepositoryTableItemVO vo15=new RepositoryTableItemVO("1","001","jtb",
                12,50.2,"ndtg","asdasd",new Date());

        RepositoryTableItemVO vo16=new RepositoryTableItemVO("1","001","yf",
                12,50.2,"dfhs","asdasd",new Date());

        RepositoryTableItemVO vo17=new RepositoryTableItemVO("1","001","tbdf",
                12,50.2,"bet","asdasd",new Date());


        List<RepositoryTableItemVO> list=new ArrayList<>();
        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        list.add(vo5);
        list.add(vo6);
        list.add(vo7);
        list.add(vo8);
        list.add(vo9);
        list.add(vo10);
        list.add(vo11);
        list.add(vo12);
        list.add(vo13);
        list.add(vo14);
        list.add(vo15);
        list.add(vo16);
        list.add(vo17);

        list.addAll(list);
        return new RepositoryTableVO(list);
    }
}
