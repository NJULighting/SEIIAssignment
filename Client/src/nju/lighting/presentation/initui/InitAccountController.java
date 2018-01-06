package nju.lighting.presentation.initui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import nju.lighting.blservice.initblservice.InitializationBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.factory.InitializationBLServiceFactory;
import nju.lighting.presentation.utils.DateHelper;
import nju.lighting.presentation.utils.TableViewHelper;
import nju.lighting.vo.InitVO;
import shared.ResultMessage;
import shared.TwoTuple;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitAccountController implements Initializable {
    @FXML
    TableView<InitVO> tableView;
    @FXML
    TableColumn<InitVO, String> creator, time, url;
    @FXML
    JFXButton init;
    @FXML
    StackPane stackPane;
    private InitializationBLService blService = InitializationBLServiceFactory.getInitializationBLService();
    private ObservableList<InitVO> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init.setOnAction(e -> {
            TwoTuple<ResultMessage, InitVO> res = blService.initiateAccount();
            DialogHelper.dialog("期初建账",res.t, stackPane);
            if (res.t == ResultMessage.SUCCESS)
                data.add(res.r);
        });


        data.setAll(blService.getInitInfo());

        tableView.setItems(data);
        creator.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getUserVO().getUsername()));

        time.setCellValueFactory(c ->
                new SimpleStringProperty(DateHelper.accurateTime(c.getValue().getTime())));

        url.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getUrl()));
        url.setCellFactory(p -> {
            return new TableCell<InitVO, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(null);
                        Hyperlink hyperlink = new Hyperlink(getItem());
                        setGraphic(hyperlink);
                        hyperlink.setTextFill(Color.BLACK);
                        hyperlink.setOnAction(r -> {
                            Desktop desktop = Desktop.getDesktop();
                            try {
                                desktop.browse(new URI(getItem()));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        });
                    }
                }
            };
        });

        TableViewHelper.commonSet(tableView);

//        TableViewHelper.setHeight(tableView,1.1);
    }
}
