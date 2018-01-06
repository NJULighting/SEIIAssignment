package shared;

import nju.lighting.bl.utils.CommodityPathParser;
import nju.lighting.po.commodity.CommodityItemPO;
import nju.lighting.vo.commodity.CommodityCategoryVO;
import nju.lighting.vo.commodity.CommodityItemVO;

import java.util.Date;

/**
 * Created on 2017/12/29.
 * Description:
 * @author Liao
 */
public class CommodityBuildInfo {

    private final String name;
    private final String modelNumber;
    private final int repCount;
    private final double inPrice;
    private final double recentInPrice;
    private final double sellPrice;
    private final double recentSellPrice;
    private final String batch;
    private final String batchNumber;
    private final Date dateOfProduction;
    private final String categoryPath;


    private CommodityBuildInfo(CommodityBuilder builder) {
        name = builder.name;
        modelNumber = builder.modelNumber;
        repCount = builder.repCount;
        inPrice = builder.inPrice;
        sellPrice = builder.sellPrice;
        batch = builder.batch;
        batchNumber = builder.batchNumber;
        dateOfProduction = builder.dateOfProduction;
        recentSellPrice = builder.recentSellPrice;
        recentInPrice = builder.recentInPrice;
        categoryPath = builder.categoryPath;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public CommodityItemPO toPO(String commodityId) {
        int categoryId = CommodityPathParser.getLastNumOfPath(categoryPath);

        return new CommodityItemPO(commodityId, name, categoryId, modelNumber, repCount, inPrice, sellPrice,
                recentInPrice, recentSellPrice, batch, batchNumber, dateOfProduction);
    }

    public CommodityItemVO toVO(String commodityId) {
        return new CommodityItemVO(commodityId, name, modelNumber, repCount, inPrice, sellPrice, inPrice, sellPrice,
                batch, batchNumber, dateOfProduction);
    }

    public static class CommodityBuilder {
        private final String categoryPath;
        private String name;
        private String modelNumber;
        private int repCount;
        private double inPrice;
        private double recentInPrice;
        private double sellPrice;
        private double recentSellPrice;
        private String batch;
        private String batchNumber;
        private Date dateOfProduction;

        public CommodityBuilder(CommodityCategoryVO parentCategory) {
            categoryPath = parentCategory.getPath();
        }

        public CommodityBuilder(String categoryPath) {
            this.categoryPath = categoryPath;
        }

        public CommodityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CommodityBuilder modelNumber(String modelNumber) {
            this.modelNumber = modelNumber;
            return this;
        }

        public CommodityBuilder inPrice(double inPrice) {
            this.inPrice = inPrice;
            return this;
        }

        public CommodityBuilder recentInPrice(double recentInPrice) {
            this.recentInPrice = recentInPrice;
            return this;
        }

        public CommodityBuilder recentSellPrice(double recentSellPrice) {
            this.recentSellPrice = recentSellPrice;
            return this;
        }

        public CommodityBuilder sellPrice(double sellPrice) {
            this.sellPrice = sellPrice;
            return this;
        }

        public CommodityBuilder batch(String batch) {
            this.batch = batch;
            return this;
        }

        public CommodityBuilder batchNumber(String batchNumber) {
            this.batchNumber = batchNumber;
            return this;
        }

        public CommodityBuilder dateOfProduction(Date dateOfProduction) {
            this.dateOfProduction = dateOfProduction;
            return this;
        }

        public CommodityBuilder repCount(int repCount) {
            this.repCount = repCount;
            return this;
        }

        public CommodityBuildInfo build() {
            return new CommodityBuildInfo(this);
        }
    }
}
