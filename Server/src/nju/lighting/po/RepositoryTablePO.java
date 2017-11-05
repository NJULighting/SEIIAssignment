package nju.lighting.po;

import java.util.ArrayList;

public class RepositoryTablePO {

    private ArrayList<RepositoryTableItemPO> commodities;

    public RepositoryTablePO(ArrayList<RepositoryTableItemPO> commodities) {
        this.commodities = commodities;
    }

    public ArrayList<RepositoryTableItemPO> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<RepositoryTableItemPO> commodities) {
        this.commodities = commodities;
    }
}
