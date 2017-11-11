package nju.lighting.bl.logbl;

/**
 * Created on 2017/11/5.
 * Description: 实现此接口的类需要实现<code>describe</code>方法，实现之后就可以被<code>DocLogger</code>调用以实现对单据操作的日志记录
 * @author Liao
 */
public interface Describable {
    /**
     * 该方法应该描述单据的创建者、种类、编号、备注
     * @return 对单据的描述
     */
    String describe();
}
