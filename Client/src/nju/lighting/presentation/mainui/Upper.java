package nju.lighting.presentation.mainui;

import javafx.scene.Node;

/**
 * Created on 2017/12/23.
 * Description 在同一个界面下需要分层显示不同的界面，则需要实现这个接口
 * @author 陈俊宇
 */
public interface Upper {
    public void back();

    public void setChildren(Node node, String title);
}
