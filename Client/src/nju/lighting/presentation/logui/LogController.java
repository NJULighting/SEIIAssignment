package nju.lighting.presentation.logui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import nju.lighting.bl.logbl.LogBLService_Stub;
import nju.lighting.blservice.logblservice.LogBLService;
import nju.lighting.vo.LogVO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created on 2017/11/30.
 * Description
 *
 * @author 陈俊宇
 */
public class LogController implements Initializable {

    ArrayList<LogVO> logVOArrayList;

    /*
    logVOArrayList中存储10页的内容，用CurrentListLevel标记当前logVOArrayList所含是第几个十页的，如一到十页level就为0
    主要用来与选定page进行比较，如过处于同一level就直接从logVoArrayList中拿，否则从数据层中拿到相应十页的内容
    并赋值给logVoArrayList，更新currentListLevel的值
     */
    int currentListLevel = 0;
    int itemPerPage = 15;
    LogBLService logBLService=new LogBLService_Stub();


//    TableView CreatePage(int pageIndex) {
//
//        ObservableList logList = FXCollections.observableArrayList();
//
//        int level = pageIndex / 10;
//        if (level != currentListLevel){
//            logVOArrayList=logBLService.getLogListByTime();
//        }
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
