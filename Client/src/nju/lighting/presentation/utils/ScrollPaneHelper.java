package nju.lighting.presentation.utils;

import com.jfoenix.controls.JFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ScrollPaneHelper {

    //调整滑动面板高度以适应总高度
    public static void marchHeight(TableView tableView, ScrollPane scrollPane, AnchorPane anchorPane) {
        double initHeight = scrollPane.getPrefHeight();
        double initAnchor = anchorPane.getPrefHeight();
        if (tableView.getPrefHeight() > initHeight) {
            scrollPane.setPrefHeight(tableView.getPrefHeight());
            anchorPane.setPrefHeight(initAnchor + tableView.getPrefHeight() - initHeight);
        }

        tableView.prefHeightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() > initHeight) {
                    scrollPane.setPrefHeight(newValue.doubleValue());
                    anchorPane.setPrefHeight(initAnchor + tableView.getPrefHeight() - initHeight);
                }
            }
        });
    }

    //滑块不会消失在视野中
    public static void sliderFload(JFXSlider slider, ScrollPane scrollPane, AnchorPane anchorPane, double prePosition, double paneSize) {

        scrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.doubleValue() * (anchorPane.getPrefHeight() - paneSize) > prePosition) {

                    slider.setLayoutY(newValue.doubleValue() * (anchorPane.getPrefHeight() - paneSize));
                }

            }
        });
    }
}
