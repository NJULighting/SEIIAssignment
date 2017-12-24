package nju.lighting.vo.commodity;

public class BasicCommodityItemVO {

    private String id;
    private String name;
    private int repCount;
    private double recentInPrice;
    private double recentSellPrice;
    private String modelNumber;

    // 过气的构造函数， 但桩里大多用的这个，先留着不删
    public BasicCommodityItemVO(String id, String name, int repCount, double recentInPrice, double recentSellPrice) {
        this.id = id;
        this.name = name;
        this.repCount = repCount;
        this.recentInPrice = recentInPrice;
        this.recentSellPrice = recentSellPrice;
    }

    public BasicCommodityItemVO(String id, String name, int repCount, double recentInPrice, double recentSellPrice,
                                String modelNumber) {
        this.id = id;
        this.name = name;
        this.repCount = repCount;
        this.recentInPrice = recentInPrice;
        this.recentSellPrice = recentSellPrice;
        this.modelNumber = modelNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    public double getRecentInPrice() {
        return recentInPrice;
    }

    public void setRecentInPrice(double recentInPrice) {
        this.recentInPrice = recentInPrice;
    }

    public double getRecentSellPrice() {
        return recentSellPrice;
    }

    public void setRecentSellPrice(double recentSellPrice) {
        this.recentSellPrice = recentSellPrice;
    }

    public String getModelNumber() {
        return modelNumber;
    }
}
