package nju.lighting.bl.approvalbl;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.bl.documentbl.DocManage;

import java.util.ArrayList;

/**
 * Created on 2017/11/13.
 * Description 审批单据的领域模型对象
 * @author 陈俊宇
 */
public class Approval {
    ArrayList<Doc> docs;

    public Approval(){
        DocManage docManage=new DocManage();
    //    this.docs=docManage.findDocuments(null);
    }
}