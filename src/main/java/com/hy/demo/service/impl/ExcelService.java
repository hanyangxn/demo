package com.hy.demo.service.impl;

import com.hy.demo.controller.StageUpAndDownController;
import com.hy.demo.emun.PositionType;
import com.hy.demo.result.MsgUtil;
import com.hy.demo.util.CONST;
import com.hy.demo.util.ExcelImportUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author hy
 * @description:
 * @date 2020/08/20
 */
@Component
@Order
public class ExcelService {

    final static Logger logger = LoggerFactory.getLogger(ExcelService.class);

    @Autowired
    private MessageSource msgsrc;

    public void test(HttpServletRequest request, HttpServletResponse response){
        try {
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/vnd.ms-excel");
            String outFileName = "组合持仓";
            String filenameSend = "";
            if (agent != null && agent.toLowerCase().indexOf("firefox") > 0) {
                filenameSend = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(outFileName.getBytes("UTF-8")))) + "?=";
            } else {
                filenameSend = java.net.URLEncoder.encode(outFileName, "UTF-8");
            }
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("content-disposition", "attachment;filename=" + filenameSend + ".xlsx");
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + filenameSend + ".xlsx");
            }
            File file = ResourceUtils.getFile("classpath:excelTemplate/position1.xlsx");
            String fileName = file.getName();
            InputStream is = new FileInputStream(file);
            Workbook wb = ExcelImportUtils.getWorkbook(is, fileName);
            is.close();

            PositionType[] values = PositionType.values();
            for (int i = 0; i < values.length; i++) {
                Sheet sheet = wb.getSheetAt(i);
                Row row = sheet.createRow(1);
                for (int j = 0; j < 14; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(j + 1);
                }
            }
            OutputStream out = response.getOutputStream();
            wb.write(out);
        }catch (Exception e){
            logger.info(CONST.EXCEL_EXCEPTION);
            throw MsgUtil.warning(CONST.EXCEL_EXCEPTION,msgsrc);
        }
    }
}
