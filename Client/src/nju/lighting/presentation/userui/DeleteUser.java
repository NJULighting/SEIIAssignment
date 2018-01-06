package nju.lighting.presentation.userui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import nju.lighting.presentation.utils.UserHelper;
import nju.lighting.vo.UserVO;

/**
 * Created on 2018/1/4.
 * Description
 *
 * @author 陈俊宇
 */
public class DeleteUser {
    @FXML
    Label userText;

    void init(UserVO user){
        userText.setText(user.toString()+"?");
    }
}
