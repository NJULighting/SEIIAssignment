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
