package nju.lighting.presentation.mainui;

import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nju.lighting.bl.userbl.UserController;
import nju.lighting.blservice.userblservice.LoginService;
import nju.lighting.blservice.userblservice.UserBLService;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.vo.UserVO;
import shared.LoginReturnState;
import shared.TwoTuple;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/27.
 * Description
 *
 * @author 陈俊宇
 */
public class LoginController extends CommonFather {


    static Parent root;

    static Stage primaryStage;

    @FXML
    private TextField account;

    @FXML
    private PasswordField password;

    @FXML
    private Button closeBtn, loginBtn, miniBtn;

    @FXML
    private Pane dialogPane, mainPane;

    @FXML
    private Button closeDialogBtn;

    @FXML
    private Label dialogLabel;

    @FXML
    private JFXSpinner spinner;


    private UserBLService userBLService = UserBLServiceFactory.getUserBLService();



    @FXML
    public void login() throws IOException {


        TwoTuple<UserVO, LoginReturnState> result = userBLService.login(account.getText(), password.getText());

        switch (result.r) {

            case INVALID_USER_NAME:
                System.out.println("Invalid User Name!");
                dialogLabel.setText("用户名错误！请尝试重新输入!");
                dialogPane.setVisible(true);
                account.requestFocus();
                break;

            case INVALID_PASSWORD:
                System.out.println("Invalid Password!");
                dialogLabel.setText("密码错误！请尝试重新输入!");
                dialogPane.setVisible(true);
                password.requestFocus();
                break;

            case UNKNOWN:
                System.out.println("Fail!");
                dialogLabel.setText("登录超时！");
                dialogPane.setVisible(true);
                break;

            case SUCCESS:
                System.out.println("Succ");
            {
                Client.setUserVO(result.t);
                mainPane.setEffect(new GaussianBlur());
                spinner.setVisible(true);
                MainUI mainUI = new MainUI(result.t.getIdentity());
                System.out.println("start");
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Platform.runLater(() -> {
                            System.out.println("1");
                            mainUI.setStage();
                        });
                    }
                }.start();
                mainUI.setStage();
            }

            break;
        }
    }

    @FXML
    public void loginByEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            login();
    }

    @FXML
    public void closeDialog(Event event) {
        dialogPane.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = Client.primaryStage;

        buttons = new Button[]{closeBtn, miniBtn};
        super.initialize(location, resources);


        //登录按钮是否可点与两textfield是否为空绑定
        loginBtn.disableProperty()
                .bind(account.textProperty().isEmpty()
                        .or(password.textProperty().isEmpty()));
        loginBtn.disableProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                //登录按钮是否高亮与disable绑定
                loginBtn.opacityProperty()
                        .bind(Bindings.createDoubleBinding(() -> (loginBtn.disableProperty().get()) ? MISS_OPACITY : 1));
            }
        });

    }

}
