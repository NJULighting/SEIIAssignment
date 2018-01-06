package nju.lighting.presentation.factory;

import nju.lighting.bl.approvalbl.ApprovalBLService_Stub;
import nju.lighting.bl.approvalbl.ApprovalController;
import nju.lighting.blservice.approvalblservice.ApprovalBLService;

/**
 * Created on 2017/12/30.
 * Description
 * @author 陈俊宇
 */
public class ApprovalBLServiceFactory {
    private static ApprovalBLService approvalBLService = new ApprovalBLService_Stub();

    public static ApprovalBLService getApprovalBLService() {
        return approvalBLService;
    }
}
