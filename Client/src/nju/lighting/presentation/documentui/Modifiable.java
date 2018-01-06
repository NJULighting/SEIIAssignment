package nju.lighting.presentation.documentui;

import nju.lighting.vo.DocVO;

/**
 * Created on 2018/1/6.
 * Description
 *
 * @author 陈俊宇
 */
public interface Modifiable {
    public void modify(DocVO docVO,boolean redFlush);
}
