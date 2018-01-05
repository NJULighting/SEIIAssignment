package nju.lighting.builder.doc;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.bl.commoditybl.CommodityInfoImpl;
import nju.lighting.bl.utils.CollectionTransformer;
import nju.lighting.po.doc.DocPO;
import nju.lighting.po.doc.salesdoc.SalesDocItemPO;
import nju.lighting.po.doc.salesdoc.SalesDocPO;
import nju.lighting.vo.commodity.BasicCommodityItemVO;
import shared.DocType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/12/31.
 * Description:
 * @author Liao
 */
public class SalesDocBuildInfo extends DocBuildInfo {
    private final int customerId;
    private final String salesman;
    private final String repository;
    private final String remarks;
    private final double beforeDiscountAmount;
    private final double discount;
    private final double voucher;
    private final double finalAmount;
    private final List<SalesDocItem> itemList;

    protected SalesDocBuildInfo(Builder builder) {
        super(builder.time, builder.creatorId, builder.type);
        this.customerId = builder.customerId;
        this.salesman = builder.salesman;
        this.repository = builder.repository;
        this.remarks = builder.remarks;
        this.beforeDiscountAmount = builder.beforeDiscountAmount;
        this.discount = builder.discount;
        this.voucher = builder.voucher;
        this.itemList = builder.itemList;
        this.finalAmount = beforeDiscountAmount * (1 - discount) - voucher;
    }

    @Override
    public DocPO toPO() {
        List<SalesDocItemPO> itemPOList = CollectionTransformer.toList(itemList, SalesDocItem::toPO);
        return new SalesDocPO(type, creatorId, time, salesman, Integer.toString(customerId),
                repository, remarks, discount, voucher, finalAmount, itemPOList);
    }

    public static class Builder extends DocBuilder {
        private List<SalesDocItem> itemList = new ArrayList<>();
        private int customerId;
        private String salesman;
        private String repository;
        private String remarks;
        private double beforeDiscountAmount;
        private double discount;
        private double voucher;

        public Builder(Date time, String creatorId, DocType type) {
            super(time, creatorId, type);
        }

        @Override
        public SalesDocBuildInfo build() {
            return null;
        }

        public void addItem(int number, String remarks, BasicCommodityItemVO commodity, double recentPrice) {
            itemList.add(new SalesDocItem(number, remarks, commodity.getId(), recentPrice));
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder salesman(String salesman) {
            this.salesman = salesman;
            return this;
        }

        public Builder repository(String repository) {
            this.repository = repository;
            return this;
        }

        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public Builder beforeDiscountAmout(double beforeDiscountAmount) {
            this.beforeDiscountAmount = beforeDiscountAmount;
            return this;
        }

        public Builder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public Builder voucher(double voucher) {
            this.voucher = voucher;
            return this;
        }
    }

    private static class SalesDocItem {
        private int number;
        private double totalAmount;
        private String remarks;
        private String commodity;
        private double recentSellPrice;

        private SalesDocItem(int number, String remarks, String commodity, double recentSellPrice) {
            this.number = number;
            this.remarks = remarks;
            this.commodity = commodity;
            this.recentSellPrice = recentSellPrice;

            totalAmount = recentSellPrice * number;
        }

        private SalesDocItemPO toPO() {
            return new SalesDocItemPO(commodity, number, totalAmount, remarks);
        }

        private void updateRecentSellPrice() {
            CommodityInfo commodityInfo = new CommodityInfoImpl();
            commodityInfo.updateCommodityRecentSellPrice(commodity, recentSellPrice);
        }
    }
}
