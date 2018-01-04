package nju.lighting.presentation.documentui.lossandgaindoc;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import nju.lighting.vo.doc.lossandgaindoc.LossAndGainDocItemVO;
import shared.LossAndGainItemType;

/**
 * Created on 2017/12/25.
 * Description
 * @author 陈俊宇
 */
public class LossAndGainItem {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleIntegerProperty count = new SimpleIntegerProperty();
    private SimpleIntegerProperty repCount = new SimpleIntegerProperty();
    private SimpleObjectProperty<LossAndGainItemType> type = new SimpleObjectProperty<>();
    private BasicCommodityItemVO commodity;


    public LossAndGainItem(BasicCommodityItemVO commodity, int number, LossAndGainItemType type) {
        this.commodity = commodity;
        name.set(commodity.getName());
        id.set(commodity.getId());
        repCount.set(commodity.getRepCount());
        count.set(number);
        this.type.set(type);
    }

    public LossAndGainItem(LossAndGainDocItemVO vo) {
        name.set(vo.getCommodity().getName());
        count.set(vo.getCount());
        id.set(vo.getCommodity().getId());
        repCount.set(vo.getCommodity().getRepCount());
        type.set(vo.getType());
    }

    public LossAndGainDocItemVO toLossAndGainDocItemVO() {
        return new LossAndGainDocItemVO(commodity, count.getValue(), type.getValue());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public int getRepCount() {
        return repCount.get();
    }

    public void setRepCount(int repCount) {
        this.repCount.set(repCount);
    }

    public SimpleIntegerProperty repCountProperty() {
        return repCount;
    }

    public LossAndGainItemType getType() {
        return type.get();
    }

    public void setType(LossAndGainItemType type) {
        this.type.set(type);
    }

    public SimpleObjectProperty<LossAndGainItemType> typeProperty() {
        return type;
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public BasicCommodityItemVO getCommodity() {
        return commodity;
    }
}
