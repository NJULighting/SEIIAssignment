package nju.lighting.presentation.utils;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import nju.lighting.vo.repository.RepositoryTableItemVO;
import nju.lighting.vo.viewtables.BusinessConditionTableVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import nju.lighting.vo.viewtables.SalesDetailItemVO;
import shared.ResultMessage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExportExcelHelper {

    public static ResultMessage repositoryCounting(List<RepositoryTableItemVO> repositoryTableList){

        String[] head=new String[]{"商品","型号","库存数量","库存均价"};

        int tableSize = repositoryTableList.size();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择保存路径");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files", "*.xls"));
        File file = fileChooser.showSaveDialog(new Stage());


        WritableWorkbook theNew= null;

        if (file != null) {
            try {

                theNew = Workbook.createWorkbook(file);
                WritableSheet sheet=theNew.createSheet("sheet 1",0);

                sheet.setColumnView(0, 25);

                //设置第一行即表头
                for(int i=0;i<head.length;i++){
                    sheet.addCell(new jxl.write.Label(i,0,head[i]));
                }

                //一行一行设置
                for(int i=0;i<tableSize;i++){
                    sheet.addCell(new jxl.write.Label(0,i+1,repositoryTableList.get(i).getCommodityName()));
                    sheet.addCell(new jxl.write.Label(1,i+1,repositoryTableList.get(i).getModelNumber()));
                    sheet.addCell(new jxl.write.Label(2,i+1,String.valueOf(repositoryTableList.get(i).getRepCount())));
                    sheet.addCell(new jxl.write.Label(3,i+1,String.valueOf(repositoryTableList.get(i).getRecentInPrice())));
                }

                theNew.write();
                theNew.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

            return ResultMessage.SUCCESS;
        }
        else{
            return ResultMessage.FAILURE;
        }

    }

    public static ResultMessage salesDetailTable(ObservableList<SalesDetailItemVO> observableList){

        String[] head=new String[]{"商品","型号","数量","单价","总额","时间"};

        int tableSize = observableList.size();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择保存路径");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files", "*.xls"));
        File file = fileChooser.showSaveDialog(new Stage());


        WritableWorkbook theNew= null;

        if (file != null) {
            try {

                theNew = Workbook.createWorkbook(file);
                WritableSheet sheet=theNew.createSheet("sheet 1",0);

                //设置第一行即表头
                for(int i=0;i<head.length;i++){
                    sheet.addCell(new jxl.write.Label(i,0,head[i]));
                }

                //一行一行设置
                for(int i=0;i<tableSize;i++){
                    sheet.addCell(new jxl.write.Label(0,i+1,observableList.get(i).getName()));
                    sheet.addCell(new jxl.write.Label(1,i+1,observableList.get(i).getCommodityType().toString()));
                    sheet.addCell(new jxl.write.Label(2,i+1,String.valueOf(observableList.get(i).getNumber())));
                    sheet.addCell(new jxl.write.Label(3,i+1,String.valueOf(observableList.get(i).getSalePrice())));
                    sheet.addCell(new jxl.write.Label(4,i+1,String.valueOf(observableList.get(i).getTotalAmount())));
                    sheet.addCell(new jxl.write.Label(1,i+1,observableList.get(i).getDate().toString()));
                }

                theNew.write();
                theNew.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

            return ResultMessage.SUCCESS;
        }
        else{
            return ResultMessage.FAILURE;
        }

    }

    public static ResultMessage businessConditionTable(BusinessConditionTableVO businessConditionTable){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择保存路径");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files", "*.xls"));
        File file = fileChooser.showSaveDialog(new Stage());


        WritableWorkbook theNew= null;

        if (file != null) {
            try {

                theNew = Workbook.createWorkbook(file);
                WritableSheet sheet=theNew.createSheet("sheet 1",0);

                //单元格格式和字体
                WritableCellFormat format = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD));
                //把水平对齐方式指定为居中
                format.setAlignment(jxl.format.Alignment.CENTRE);
                //把垂直对齐方式指定为居中
                format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
                //设置自动换行
                format.setWrap(true);

                //宽度和合并单元格设置
                sheet.setColumnView(0, 8);
                sheet.setColumnView(1, 14);
                sheet.setColumnView(2, 18);
                sheet.setColumnView(3, 14);
                sheet.mergeCells(0, 0, 0, 5);
                sheet.mergeCells(0, 7, 0, 10);
                sheet.mergeCells(1, 1, 1, 4);
                sheet.mergeCells(1, 8, 1, 9);
                sheet.mergeCells(0, 0, 0, 5);
                sheet.mergeCells(0, 6, 3, 6);
                sheet.mergeCells(0, 11, 3, 11);

                //各大小标题
                sheet.addCell(new jxl.write.Label(0,0,"收入",format));
                sheet.addCell(new jxl.write.Label(0,7,"支出",format));
                sheet.addCell(new jxl.write.Label(0,12,"利润",format));
                sheet.addCell(new jxl.write.Label(1,0,"销售收入",format));
                sheet.addCell(new jxl.write.Label(1,1,"商品类收入",format));
                sheet.addCell(new jxl.write.Label(1,5,"总额",format));
                sheet.addCell(new jxl.write.Label(1,7,"销售成本",format));
                sheet.addCell(new jxl.write.Label(1,8,"商品类支出",format));
                sheet.addCell(new jxl.write.Label(1,10,"总额",format));
                sheet.addCell(new jxl.write.Label(2,1,"商品报溢收入",format));
                sheet.addCell(new jxl.write.Label(2,2,"成本调价收入",format));
                sheet.addCell(new jxl.write.Label(2,3,"进退货差额",format));
                sheet.addCell(new jxl.write.Label(2,4,"代金券差额",format));
                sheet.addCell(new jxl.write.Label(2,8,"商品报损支出",format));
                sheet.addCell(new jxl.write.Label(2,9,"商品增出支出",format));
                sheet.addCell(new jxl.write.Label(4,0,"折让",format));

                //各数据
                sheet.addCell(new jxl.write.Label(3,0,String.valueOf(businessConditionTable.getSalesRevenue())));
                sheet.addCell(new jxl.write.Label(3,1,String.valueOf(businessConditionTable.getCommodityGainRevenue())));
                sheet.addCell(new jxl.write.Label(3,2,String.valueOf(businessConditionTable.getCostAdjustRevenue())));
                sheet.addCell(new jxl.write.Label(3,3,String.valueOf(businessConditionTable.getSpreadRevenue())));
                sheet.addCell(new jxl.write.Label(3,4,String.valueOf(businessConditionTable.getVoucherCausedRevenue())));
                sheet.addCell(new jxl.write.Label(3,5,String.valueOf(businessConditionTable.getRevenue())));
                sheet.addCell(new jxl.write.Label(3,7,String.valueOf(businessConditionTable.getCostExpenditure())));
                sheet.addCell(new jxl.write.Label(3,8,String.valueOf(businessConditionTable.getSalesRevenue())));
                sheet.addCell(new jxl.write.Label(3,9,String.valueOf(businessConditionTable.getGiftExpenditure())));
                sheet.addCell(new jxl.write.Label(3,10,String.valueOf(businessConditionTable.getExpenditure())));
                sheet.addCell(new jxl.write.Label(3,12,String.valueOf(businessConditionTable.getProfit())));

                sheet.addCell(new jxl.write.Label(5,0,String.valueOf(businessConditionTable.getSalesRevenueOff())));


                theNew.write();
                theNew.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

            return ResultMessage.SUCCESS;
        }
        else{
            return ResultMessage.FAILURE;
        }

    }

    public static ResultMessage businessHistoryTable(ObservableList<BusinessHistoryItemVO> observableList) {

        String[] head = new String[]{"单据类型", "客户", "创建人", "创建时间", "业务员"};

        int tableSize = observableList.size();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择保存路径");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files", "*.xls"));
        File file = fileChooser.showSaveDialog(new Stage());


        WritableWorkbook theNew = null;

        if (file != null) {
            try {

                theNew = Workbook.createWorkbook(file);
                WritableSheet sheet = theNew.createSheet("sheet 1", 0);

                sheet.setColumnView(2,10);
                sheet.setColumnView(3,20);
                sheet.setColumnView(4,10);


                //设置第一行即表头
                for (int i = 0; i < head.length; i++) {
                    sheet.addCell(new jxl.write.Label(i, 0, head[i]));
                }

                //一行一行设置
                for (int i = 0; i < tableSize; i++) {
                    sheet.addCell(new jxl.write.Label(0, i + 1, observableList.get(i).getDocVO().getType().toString()));
                    sheet.addCell(new jxl.write.Label(1, i + 1, observableList.get(i).getCustomer().toString()));
                    sheet.addCell(new jxl.write.Label(2, i + 1, observableList.get(i).getDocVO().getCreatorId().toString()));
                    sheet.addCell(new jxl.write.Label(3, i + 1, observableList.get(i).getDate().toString()));
                    sheet.addCell(new jxl.write.Label(4, i + 1, observableList.get(i).getSalesman().toString()));
                }

                theNew.write();
                theNew.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.FAILURE;
        }


    }
}
