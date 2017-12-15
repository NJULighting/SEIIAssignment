package nju.lighting.presentation.documentui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.utils.ImageViewHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.runtime.regexp.joni.constants.StackType.POS;

/**
 * Created on 2017/12/9.
 * Description
 *
 * @author 陈俊宇
 */
public class SalesConditionTable implements Initializable {
    JFXNodesList nodeList;
    JFXButton btn;
    ImageView open = ImageViewHelper.circleOpen();
    ImageView close = ImageViewHelper.circleClose();
    final ImageView imageBtn = open;
    HamburgerBasicCloseTransition burgerTask;

    private static String CIRCLE_BUTTON = "circle-button";

    @FXML
    AnchorPane container;






    public JFXNodesList getNodesList() {
        return nodeList;
    }

    public void close() {
        btn.setGraphic(open);
    }

    void initBtn() {
//
        btn = new JFXButton("", open);

        btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        btn.getStylesheets().add(getClass().getResource("../circleButton.css").toExternalForm());

//        imageBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (nodesList.isExpanded())
//                    imageBtn.setImage(open.getImage());
//                else
//                    imageBtn.setImage(close.getImage());
//            }
//        });

        btn.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                if (nodeList.isExpanded())
                    btn.setGraphic(close);
                else
                    btn.setGraphic(open);
            }
        });
    }


    HamburgerBasicCloseTransition getBurgTask() {
        return burgerTask;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SalesConditionFilter.fxml"));
            Pane filterBox = loader.load();
            ((SalesConditionFilter) loader.getController()).setSalesConditionTable(this);


            JFXHamburger hamburger =new JFXHamburger();
            nodeList=new JFXNodesList();
            burgerTask = new HamburgerBasicCloseTransition(hamburger);

            burgerTask.setRate(-1);

            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {

                burgerTask.setRate(burgerTask.getRate() * -1);

                burgerTask.play();

            });



            nodeList.setSpacing(30);

//            initBtn();
           // nodesList.addAnimatedNode(btn);
            nodeList.getChildren().clear();
            nodeList.addAnimatedNode(hamburger);
            nodeList.addAnimatedNode(filterBox);

            nodeList.setMargin(hamburger,new Insets(0,0,0,990));
            nodeList.setMargin(filterBox,new Insets(-40,0,0,20));
            nodeList.setPadding(new Insets(25,0,0,40));
            container.getChildren().add(nodeList);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
