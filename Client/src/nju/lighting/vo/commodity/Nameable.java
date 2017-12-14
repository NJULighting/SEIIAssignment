package nju.lighting.vo.commodity;

/**
 * Created on 2017/12/14.
 * Description
 *因为显示商品分类时需要因为TreeView 里面包含 CommodityVO 和 CateGoryVO
 * 而设置 treecell 里面文字 的时候都是用到他们的getName 方法，所以提出一个接口， 方便声明
 * @author 陈俊宇
 */
public interface Nameable {
    public  String getName();
}
