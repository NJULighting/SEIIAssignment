package nju.lighting.presentation.utils;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Created on 2018/1/10.
 * Description
 *
 * @author 陈俊宇
 */
public class Helper {

    /**
     * 当一个主界面下有多个子界面时需要 可以通过点击标题标签跳转界面
     * @param title 该标签的名称
     * @param container 主界面的容器
     * @param labelBox 标签存放的容器
     * @param node 点击标签后要跳转的目标界面
     * @param list 持有所有所有子界面的容器
     * @return 注册了点击后界面跳转的 标签
     */
    public static Label getTitleLabel(String title, HBox container, HBox labelBox, Node node, ObservableList<Node> list) {
        Label label = new Label(title);
        label.setFont(Font.font(20));
        label.setOnMouseClicked(e -> {
            container.getChildren().setAll(node);
            labelBox.getChildren().remove(labelBox.getChildren().indexOf(label) + 1,
                    labelBox.getChildren().size());
            list.remove(list.indexOf(node) + 1, list.size());
        });

        return label;
    }

    /**
     * 当跳转多级时，需要清空改标签以后的 所有标签
     * @param container
     * @param labelBox
     * @param list
     */
    public static void clearTitleLabel(HBox container, HBox labelBox, ObservableList<Node> list) {
        labelBox.getChildren().remove(labelBox.getChildren().get(labelBox.getChildren().size() - 1));
        container.getChildren().setAll(list.get(list.size() - 2));
        list.remove(list.size() - 1);
    }

    public static void bakToMain(HBox container, HBox labelBox, ObservableList<Node> list,Node mainPane){
        list.clear();
        labelBox.getChildren().clear();
        container.getChildren().setAll(mainPane);
    }
}
