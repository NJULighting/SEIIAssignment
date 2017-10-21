package nju.lighting.vo;

import java.util.ArrayList;

public class RepositoryTableVO {

    private ArrayList<RepositoryTableItemVO> commodities;

    public ArrayList<RepositoryTableItemVO> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<RepositoryTableItemVO> commodities) {
        this.commodities = commodities;
    }

    public RepositoryTableVO(ArrayList<RepositoryTableItemVO> commodities) {
        this.commodities = commodities;
    }
}