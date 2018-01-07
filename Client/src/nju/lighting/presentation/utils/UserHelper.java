package nju.lighting.presentation.utils;

import com.jfoenix.controls.JFXComboBox;
import nju.lighting.presentation.factory.UserBLServiceFactory;
import nju.lighting.vo.UserVO;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.stream.Collectors;

/**
 * Created on 2018/1/4.
 * Description
 *
 * @author 陈俊宇
 */
public class UserHelper {
    public static UserVO getUser(String id) {
        return UserBLServiceFactory.getUserBLService().getUser(id);
    }

    public static String getUserString(String id){
        UserVO userVO=getUser(id);
        if (userVO==null)
            return "";
        else
            return userVO.toString();
    }

    public static void  intiUserBox(JFXComboBox<UserVO> box){
        box.getItems().addAll(UserBLServiceFactory.getUserBLService().getUserList());
    }

    public static UserVO getSalesman(JFXComboBox<UserVO> salesmanBox, String id) {

        return salesmanBox.getItems().stream()
                .filter(x -> x.getID().equals(id))
                .collect(Collectors.toList()).get(0);
    }
}
