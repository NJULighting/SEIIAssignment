package nju.lighting.presentation.accountui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import nju.lighting.bl.accountbl.AccountBLService_Stub;
import nju.lighting.blservice.accountblservice.AccountBLService;
import nju.lighting.presentation.DialogUI.DialogHelper;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.vo.account.AccountLogVO;
import nju.lighting.vo.account.AccountVO;
import shared.ResultMessage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created on 2017/12/27.
 * Description
 *
 * @author 陈俊宇
 */
public class AccountList  implements Initializable{
    AccountBLService blService=new AccountBLService_Stub();

    @FXML
    TableView<AccountVO> accountTableView;

    @FXML
    TableColumn<AccountVO, String> accountName;

    @FXML
    TableColumn<AccountVO, Double> balance;

    @FXML
    Button searchBtn;

    @FXML
    TextField searchText;

    ObservableList<AccountVO> accountVOObservableList = FXCollections.observableArrayList();
    ObservableList<AccountLogVO> logVOObservableList;

    @FXML
    void search() {
        String keyWord = searchText.getText();

        if (keyWord != null && keyWord.length() != 0) {

            List<AccountVO> result = blService.findAccounts(keyWord);
            if (result != null)
                accountVOObservableList.setAll(result);
            else accountVOObservableList.clear();
            logVOObservableList.clear();
        } else
            accountVOObservableList.setAll(blService.getAccountList());

    }

    void setEditable(StackPane stackPane ,ObservableList<AccountLogVO> logVOObservableList){
        SimpleObjectProperty<AccountVO> selected=new SimpleObjectProperty<>();
        this.logVOObservableList=logVOObservableList;
        //账户列表右击菜单栏
        //删除按钮
        MenuItem delete = new MenuItem("删除");
        delete.setOnAction(e -> {
            AccountVO accountVO = accountTableView.getSelectionModel().getSelectedItem();
            EventHandler deleteHandle = new EventHandler() {
                @Override
                public void handle(Event event) {
                    ResultMessage resultMessage = blService.deleteAccount(accountVO.getId());
                    System.out.println("delete Account id:" + accountVO.getId());
                    if (resultMessage == ResultMessage.SUCCESS) {
                        accountVOObservableList.remove(accountVO);
                    } else
                        DialogHelper.dialog(resultMessage, stackPane);
                }
            };
            DialogHelper.addDialog("你确定要删除账户" + accountVO.getName() + "?",
                    stackPane, deleteHandle);
        });
        MenuItem modify = new MenuItem("修改账户");
        modify.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAccount.fxml"));
            try {
                Node node = loader.load();
                AddAccount controller = loader.getController();
                controller.setModify();

                ValidateEventHandle modifyHandle = new ValidateEventHandle() {
                    @Override
                    public boolean validate() {
                        String id = controller.getId(), name = controller.getName();
                        ResultMessage resultMessage = blService.modifyAccount(id, name);
                        if (resultMessage == ResultMessage.SUCCESS) {
                            int index = accountTableView.getSelectionModel().getSelectedIndex();
                            AccountVO accountVO = accountTableView.getItems().get(index);
                            accountVO.setId(id);
                            accountVO.setName(name);
                            accountTableView.getItems().set(index,
                                    accountVO);
                            return true;
                        } else
                            DialogHelper.dialog(resultMessage, stackPane);

                        return false;
                    }
                };

                DialogHelper.addDialog(node, stackPane, modifyHandle);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        accountTableView.setContextMenu(new ContextMenu(delete, modify));

        accountVOObservableList.addListener(new ListChangeListener<AccountVO>() {
            @Override
            public void onChanged(Change<? extends AccountVO> c) {
               while (c.next()){
                   if (accountVOObservableList.size()==0||c.getRemoved().contains(selected.getValue()))
                       logVOObservableList.clear();
               }

            }
        });

        //账户列表左击显示具体条目
        accountTableView.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                System.out.println("clicked");
                if (!accountTableView.getSelectionModel().isEmpty()) {
                    selected.setValue( accountTableView.getSelectionModel().getSelectedItem());
                    List<AccountLogVO> list =selected.getValue().getAccountLogs();
                    if (list != null)
                        logVOObservableList.setAll(list);
                    else
                        logVOObservableList.clear();

                }

            }
        });

    }



    public ObservableList<AccountVO> getAccountVOObservableList() {
        return accountVOObservableList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchBtn.setOnAction(e->{search();});
        searchText.setOnAction(e->{search();});

        //表格数据初始化
        accountVOObservableList.addAll(blService.getAccountList());

        accountName.setCellValueFactory(x ->
                new SimpleStringProperty(x.getValue().getName()));

        balance.setCellValueFactory((x ->
                new SimpleDoubleProperty(x.getValue().getAmount()).asObject()));

        accountTableView.setItems(accountVOObservableList);

    }

    public AccountVO getAccount(){
        return accountTableView.getSelectionModel().getSelectedItem();
    }
}
