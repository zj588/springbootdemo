package com.imooc.jay.util;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * @author jrk.ji
 * @description
 * @date 2018/8/22 下午5:32
 */

public class ExcelUtil {

    // title 样式。水平居中。垂直居中。字体加粗
    public static HSSFCellStyle rowTitle(HSSFWorkbook workbook, HSSFCellStyle style) {
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        font.setFontHeightInPoints((short)12);
        // 字体增粗
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        return style;
    }

    // 基本样式：水平居中。垂直居中。
    public static HSSFCellStyle baseCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    public static HSSFCell baseCell(HSSFCellStyle cellStyle, HSSFRow row, int col) {
        HSSFCell cell = row.createCell(col);
        cell.setCellStyle(cellStyle);
        return cell;
    }

    public static HSSFHyperlink createHyperlink(HSSFWorkbook workbook, String url) {
        HSSFHyperlink link = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
        link.setAddress(url);
        return link;
    }

    public static HSSFWorkbook baseExcel(String[] titleArr, Integer[] columnWidthArr) {
        // 1. 创建工作薄 和 工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet");

        HSSFCellStyle titleCellStyle = baseCellStyle(workbook);

        // 2 title
        HSSFRow row = sheet.createRow(0);
        for (int col = 0; col < titleArr.length; col++) {
            HSSFCell cell = row.createCell(col);
            cell.setCellValue(titleArr[col]);
            cell.setCellStyle(rowTitle(workbook, titleCellStyle));

            if (columnWidthArr != null && columnWidthArr.length > col) {
                sheet.setColumnWidth(col, columnWidthArr[col]);
            }
        }
        return workbook;
    }
}
