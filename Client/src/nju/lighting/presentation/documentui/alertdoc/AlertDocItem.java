package nju.lighting.presentation.documentui.alertdoc;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.alertdoc.AlertDocItemVO;

public class AlertDocItem {
    SimpleStringProperty commodityName = new SimpleStringProperty();
    SimpleStringProperty commodityId = new SimpleStringProperty();
    SimpleIntegerProperty alertAccount = new SimpleIntegerProperty();
    SimpleIntegerProperty repository = new SimpleIntegerProperty();
    BasicCommodityItemVO commodity;


    //创建时只需要商品和数量
    public AlertDocItem(BasicCommodityItemVO commodity, int count) {
        this.commodity = commodity;
        this.alertAccount.set(count);
        init(commodity);
    }

    //查看单据时由AlertDocItemVO 构造
    public AlertDocItem(AlertDocItemVO vo) {
        alertAccount.set(vo.getCount());
        init(vo.getCommodity());
    }

    void init(BasicCommodityItemVO commodity) {
        commodityId.set(commodity.getId());
        commodityName.set(commodity.getName());
        repository.set(commodity.getRepCount());
    }
}
