package nju.lighting.presentation.promotionui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nju.lighting.presentation.mainui.PromotionUpper;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.promotion.PromotionVO;

import java.io.IOException;
import java.util.List;

/**
 * Created on 2017/12/20.
 * Description
 *
 * @author 陈俊宇
 */
public class BenefitsPlan {
    @FXML
    Pagination pagination;

    List <PromotionVO> promotionList;

    PromotionUpper upper;

    PromotionVO promotionVO;

    boolean canceled=true;

    @FXML
    void ok(){
        upper.setPromotion(promotionList.get(pagination.getCurrentPageIndex()));
        upper.back();
    }

    ScrollPane createPage(int index)  {
        PromotionVO promotion=promotionList.get(index);
        ScrollPane scrollPane=new ScrollPane();
        scrollPane.setPrefSize(600,600);
        Promotion.promotion=promotion;
        try {
            scrollPane.setContent( FXMLLoader.load(getClass().getResource(Promotion.hashMap.get(promotion.getType()))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrollPane;
    }


    public void init(List<PromotionVO> list,PromotionUpper upper){
        this.upper=upper;
        promotionList=list;
        pagination.setPageCount(promotionList.size());
        pagination.setPageFactory((Integer index) -> createPage(index));
        pagination.getStylesheets().add(getClass().getResource("../benefitsplan.css").toExternalForm());

    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public PromotionVO getPromotionVO() {
        return promotionVO;
    }
}
