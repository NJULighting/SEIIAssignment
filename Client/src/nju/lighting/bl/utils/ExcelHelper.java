package nju.lighting.bl.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.util.List;

/**
 * Created on 2017/12/30.
 * Description:
 *
 * @author iznauy
 */
public class ExcelHelper {

    private static HSSFWorkbook makeExcelHead(String title, int cellRangeAddressLength){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle styleTitle = createStyle(workbook, (short)16);
        HSSFSheet sheet = workbook.createSheet(title);
        sheet.setDefaultColumnWidth(25);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, cellRangeAddressLength);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFRow rowTitle = sheet.createRow(0);
        HSSFCell cellTitle = rowTitle.createCell(0);
        cellTitle.setCellValue(title);
        cellTitle.setCellStyle(styleTitle);
        styleTitle.setAlignment(HorizontalAlignment.CENTER);
        return workbook;
    }

    private static HSSFWorkbook makeSecondHead(HSSFWorkbook workbook, String[] secondTitles){
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow rowField = sheet.createRow(1);
        HSSFCellStyle styleField = createStyle(workbook, (short)13);
        for (int i = 0; i < secondTitles.length; i++) {
            HSSFCell cell = rowField.createCell(i);
            cell.setCellValue(secondTitles[i]);
            cell.setCellStyle(styleField);
        }
        return workbook;
    }

    private static <T> HSSFWorkbook exportExcelData(HSSFWorkbook workbook, List<T> dataList, String[] beanPropertys) throws Exception {
        HSSFSheet sheet = workbook.getSheetAt(0);
        // 填充数据
        HSSFCellStyle styleData = workbook.createCellStyle();

        for (int j = 0; j < dataList.size(); j++) {
            HSSFRow rowData = sheet.createRow(j + 2);
            T t = dataList.get(j);
            for(int k = 0; k < beanPropertys.length; k++){
                Object value = BeanUtils.getProperty(t, beanPropertys[k]);
                HSSFCell cellData = rowData.createCell(k);
                if (value == null) {
                    cellData.setCellValue("null");
                } else {
                    cellData.setCellValue(value.toString());
                }
                cellData.setCellStyle(styleData);
            }
        }
        return workbook;
    }

    /**
     * 导出excel的方法
     * @param file 给定一个已经存在的xls后缀的文件
     * @param objects 要导出excel的对象的集合
     * @param head 大字标题
     * @param headSize 大字标题的size，一般和大字标题汉字数相等就ok
     * @param secondProperty 每个属性的中文名称
     * @param beanProperty 每个属性的中文名称对应的原本object对象的字段名称（出现在代码中的名称）
     * @param <T> 要被续的对象
     * @return 返回是否续成功
     */
    public static <T> boolean exportExcel(File file, List<T> objects, String head, int headSize, String[] secondProperty, String[] beanProperty) {
        try {
            HSSFWorkbook workbook1 = makeExcelHead(head, headSize);
            HSSFWorkbook workbook2 = makeSecondHead(workbook1, secondProperty);
            HSSFWorkbook workbook = exportExcelData(workbook2, objects, beanProperty);
            workbook.write(file);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private static HSSFCellStyle createStyle(HSSFWorkbook workbook, short fontSize){
        HSSFCellStyle style = workbook.createCellStyle();
        // 创建一个字体样式
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        style.setFont(font);
        return style;
    }

}
