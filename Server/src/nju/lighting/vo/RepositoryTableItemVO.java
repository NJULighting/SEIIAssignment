package nju.lighting.vo;

public class RepositoryTableItemVO {

    private CommodityVO commodity;

    public CommodityVO getCommodity() {
        return commodity;
    }

    public void setCommodity(CommodityVO commodity) {
        this.commodity = commodity;
    }

    public RepositoryTableItemVO(CommodityVO commodity) {
        this.commodity = commodity;
    }

}
