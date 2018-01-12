package shared;

/**
 * Created on 2017/11/6.
 * Description
 * @author 陈俊宇
 */
public enum PromotionType {
    CustomerOriented("针对客户"),
    Combo("组合商品降价"),
    PriceOriented("针对总价");

    private final String text;

    private PromotionType(String text){
        this.text=text;
    }


    @Override
    public String toString() {
        return text;
    }
}
