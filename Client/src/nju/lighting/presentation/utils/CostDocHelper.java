package nju.lighting.presentation.utils;

import shared.CostDocItemType;

import java.util.HashMap;

/**
 * Created on 2018/1/4.
 * Description
 *
 * @author 陈俊宇
 */
public class CostDocHelper {
    private static HashMap<CostDocItemType, String> typeStringHashMap = new HashMap<>();
    private static HashMap<String,CostDocItemType> stringTypeHashMap=new HashMap<>();

    private final static String ACCOMMODATION="住宿";
    private final static String BUSINESS_TRIP="出差";
    private final static String COMMUNICATION="交流";
    private final static String MEAL="饮食";
    private final static String MEETING="会议";
    private final static String OFFICE="办公室";
    private final static String TRANSPORTATION="交通";
    private final static String RECEPTOIN="收据";
    private final static String OTHER="其他";


    static {
        typeStringHashMap.put(CostDocItemType.ACCOMMODATION, ACCOMMODATION);
        typeStringHashMap.put(CostDocItemType.BUSINESS_TRIP, BUSINESS_TRIP);
        typeStringHashMap.put(CostDocItemType.COMMUNICATION, COMMUNICATION);
        typeStringHashMap.put(CostDocItemType.MEAL, MEAL);
        typeStringHashMap.put(CostDocItemType.MEETING, MEETING);
        typeStringHashMap.put(CostDocItemType.OFFICE, OFFICE);
        typeStringHashMap.put(CostDocItemType.TRANSPORTATION, TRANSPORTATION);
        typeStringHashMap.put(CostDocItemType.RECEPTOIN, RECEPTOIN);
        typeStringHashMap.put(CostDocItemType.OTHER, OTHER);

        stringTypeHashMap.put(ACCOMMODATION,CostDocItemType.ACCOMMODATION);
        stringTypeHashMap.put(BUSINESS_TRIP,CostDocItemType.BUSINESS_TRIP);
        stringTypeHashMap.put(COMMUNICATION,CostDocItemType.COMMUNICATION);
        stringTypeHashMap.put(MEAL,CostDocItemType.MEAL);
        stringTypeHashMap.put(MEETING,CostDocItemType.MEETING);
        stringTypeHashMap.put(OFFICE,CostDocItemType.OFFICE);
        stringTypeHashMap.put(TRANSPORTATION,CostDocItemType.TRANSPORTATION);
        stringTypeHashMap.put(RECEPTOIN,CostDocItemType.RECEPTOIN);
        stringTypeHashMap.put(OTHER,CostDocItemType.OTHER);



    }

    public static String typeToString(CostDocItemType type){return typeStringHashMap.get(type);}

    public static CostDocItemType stringToType(String string){return stringTypeHashMap.get(string);}
}
