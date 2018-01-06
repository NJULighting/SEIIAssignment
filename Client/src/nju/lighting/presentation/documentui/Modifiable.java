package nju.lighting.presentation.documentui;

import javafx.scene.Node;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.DocVO;

/**
 * Created on 2018/1/6.
 * Description
 *
 * @author 陈俊宇
 */
public interface Modifiable {
    public void modify(Upper upper, DocVO docVO, boolean redFlush);

    public Node getMainPane();
}
