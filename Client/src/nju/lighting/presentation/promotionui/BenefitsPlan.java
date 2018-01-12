package nju.lighting.presentation.promotionui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import nju.lighting.presentation.mainui.Upper;
import nju.lighting.vo.promotion.PromotionVO;

import java.io.IOException;
import java.util.List;

/**
 * Created on 2017/12/20.
 * Description 显示符合条件的促销策略，用于制定销售单时
 * @author 陈俊宇
 */
public class BenefitsPlan {
    @FXML
    Pagination pagination;

    @FXML
    HBox promotionContainer;

    List<PromotionVO> promotionList;

    Upper upper;

    SimpleObjectProperty<PromotionVO> promotionProperty;
    PromotionVO promotionVO;

    boolean canceled = true;

    @FXML
    void ok() {
        promotionProperty.set(promotionList.get(pagination.getCurrentPageIndex()));
        upper.back();
    }

    ScrollPane createPage(int index) {
        PromotionVO promotion = promotionList.get(index);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(600, 600);
        Promotion.promotion = promotion;
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Promotion.fxml"));
            scrollPane.setContent(loader.load());
            ((Promotion) loader.getController()).init(promotion);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return scrollPane;
    }


    public void init(List<PromotionVO> list, Upper upper, SimpleObjectProperty<PromotionVO> promotionProperty) {
        this.upper = upper;
        this.promotionProperty = promotionProperty;
        promotionList = list;
        pagination.setPageCount(promotionList.size());
        pagination.setPageFactory((Integer index) -> createPage(index));
        pagination.getStylesheets().add(getClass().getClassLoader().getResource("nju/lighting/presentation/benefitsplan.css").toExternalForm());

    }


}
