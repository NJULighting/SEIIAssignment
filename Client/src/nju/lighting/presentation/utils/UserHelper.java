package nju.lighting.presentation.utils;

import shared.Identity;

import java.util.HashMap;

/**
 * Created on 2018/1/4.
 * Description
 *
 * @author 陈俊宇
 */
public class UserHelper {
    private static HashMap<String, Identity> stringIdentityHashMap = new HashMap<>();
    private static HashMap<Identity, String> identityStringHashMap = new HashMap<>();

    static {
        identityStringHashMap.put(Identity.GENERAL, "总经理");
        identityStringHashMap.put(Identity.FINANCE, "财务人员");
        identityStringHashMap.put(Identity.REPOSITORY, "库存人员");
        identityStringHashMap.put(Identity.SALE, "销售人员");
        identityStringHashMap.put(Identity.SALE_MANAGER, "销售经理");
        identityStringHashMap.put(Identity.SYSTEM_ADMIN, "管理员");

        stringIdentityHashMap.put("总经理", Identity.GENERAL);
        stringIdentityHashMap.put("财务人员", Identity.FINANCE);
        stringIdentityHashMap.put("库存人员", Identity.REPOSITORY);
        stringIdentityHashMap.put("销售人员", Identity.SALE);
        stringIdentityHashMap.put("销售经理", Identity.SALE_MANAGER);
    }

    public static String identityToString(Identity identity) {
        return identityStringHashMap.get(identity);
    }

    public static Identity stringToIdentity(String string) {
        return stringIdentityHashMap.get(string);
    }
}
