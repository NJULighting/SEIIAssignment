package nju.lighting.presentation.documentui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import nju.lighting.presentation.DialogUI.ValidateEventHandle;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.presentation.utils.DocHelper;
import nju.lighting.vo.DocVO;

/**
 * Created on 2018/1/10.
 * Description  添加单据控制类的抽象父类，实现了修改的方法（包括红冲和总经理审批时修改）
 *
 * @author 陈俊宇
 */
public abstract class AddDoc implements Modifiable {
    protected abstract DocVO getDoc();
    public Button commitBtn;
    public Pane mainPane;

    @Override
    public void modify(Upper upper, DocVO docVO, boolean redFlush) {
        SimpleObjectProperty<DocVO> res =  new SimpleObjectProperty<>();

        ValidateEventHandle commit=new ValidateEventHandle() {
            @Override
            public boolean validate() {
                res.set(getDoc());
                if (res.getValue()!=null){
                    res.getValue().setCreatorId(docVO.getCreatorId());
                    res.getValue().setDocId(docVO.getDocId());
                    return true;
                }
                else
                    return false;
            }
        };
        DocHelper.commitModifiedDoc(redFlush,commitBtn,commit,res,upper);
    }

    @Override
    public Node getMainPane() {
        return mainPane;
    }
}
