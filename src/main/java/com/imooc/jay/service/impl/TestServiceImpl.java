package com.imooc.jay.service.impl;

import com.imooc.jay.entity.TbArea;
import com.imooc.jay.service.TestService;
import com.imooc.jay.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public HSSFWorkbook exportTest() throws IOException {
        List<TbArea> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            TbArea area = new TbArea();
            area.setId(i);
            area.setName("地区_" + i);
            area.setPriority(i);

            list.add(area);
        }

        String[] titleArr = new String[] {"id", "name", "priority"};

        Integer[] columnWidthArr = new Integer[] {10 * 256, 10 * 256, 10 * 256};

        HSSFWorkbook workbook = ExcelUtil.baseExcel(titleArr, columnWidthArr);

        HSSFCellStyle cellStyle = ExcelUtil.baseCellStyle(workbook);

        HSSFSheet sheet = workbook.getSheetAt(0);
        int rowIndex = 1;
        for (TbArea area : list) {
            HSSFRow row = sheet.createRow(rowIndex++);

            int col = 0;
            // 状态。eg：观察中
            HSSFCell cell = ExcelUtil.baseCell(cellStyle, row, col);
            cell.setCellValue(area.getId());

            // 教师 ID。eg：6971
            cell = ExcelUtil.baseCell(cellStyle, row, ++col);
            cell.setCellValue(area.getName());

            // 教师姓名。eg：spring
            cell = ExcelUtil.baseCell(cellStyle, row, ++col);
            cell.setCellValue(area.getPriority());
            cell.setHyperlink(ExcelUtil.createHyperlink(workbook, "www.baidu.com"));
        }
        return workbook;
    }

    @Override
    public void testSchedule() {
        System.out.println(1);
    }
}
