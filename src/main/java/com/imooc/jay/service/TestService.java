package com.imooc.jay.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

public interface TestService {
    HSSFWorkbook exportTest() throws IOException;
}
