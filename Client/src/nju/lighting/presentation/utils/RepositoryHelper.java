package nju.lighting.presentation.utils;

import shared.RepositoryChangeType;

import java.util.HashMap;

/**
 * Created on 2017/12/21.
 * Description
 *
 * @author 陈俊宇
 */
public class RepositoryHelper {
    static HashMap<RepositoryChangeType,String> hashMap=new HashMap<>();

    static {
        hashMap.put(RepositoryChangeType.BUY,"进货");
        hashMap.put(RepositoryChangeType.BE_RETURN,"被退货");
        hashMap.put(RepositoryChangeType.GAIN,"报溢");
        hashMap.put(RepositoryChangeType.LOSS,"报损");
        hashMap.put(RepositoryChangeType.SELL,"销售");
        hashMap.put(RepositoryChangeType.RETURN,"退货");

    }

     public static String typeToString(RepositoryChangeType type){return hashMap.get(type);}
}
