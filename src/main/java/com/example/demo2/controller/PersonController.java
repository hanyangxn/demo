package com.example.demo2.controller;

import com.example.demo2.domain.Person;
import com.example.demo2.service.PersonService;
import com.example.demo2.util.ExcelImportUtils;
import com.example.demo2.util.PagedResult;
import com.example.demo2.util.ResultDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/person")
    public ResultDto<PagedResult<Person>> test(Integer pageNo, Integer pageSize) {
        return personService.selectList(pageNo, pageSize);
    }


    @RequestMapping("/download")
    public void downloadKB(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        System.out.println("=================================>" + agent);
        response.setContentType("application/vnd.ms-excel");
        String outFileName = "测试模板";
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
        File file = ResourceUtils.getFile("classpath:excelTemplate/template.xlsx");
        String fileName = file.getName();
        Workbook wb = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            //根据文件名判断文件是2003版本还是2007版本
            wb = ExcelImportUtils.getWorkbook(is, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Sheet sheet = wb.getSheetAt(0);
        String question = "";
        String answer = "";
        question = "你的车呢";
        answer = "丢了啊";
        //插入数据到excel
        Row row = sheet.createRow(0 + 2);//从第三行开始
        Cell first = row.createCell(0);
        first.setCellValue(question);
        Cell second = row.createCell(1);
        second.setCellValue(answer);

        OutputStream out = response.getOutputStream();
        wb.write(out);

    }

    @ResponseBody
    @RequestMapping(value = "/batchImport", method = RequestMethod.POST)
    public String batchImportUserKnowledge(@RequestParam(value = "file") MultipartFile file) {

        long startMili = System.currentTimeMillis();// 当前时间对应的毫秒数
        //判断文件是否为空
        if (file == null) {
            return ("文件不能为空");
        }
        long size = file.getSize();
        long big = 1024 * 128;
        System.out.println("文件大小为：" + size);
        if (size > big) {
            return ("文件大小超过128kb,");
        }
        //获取文件名
        int filesize = file.getOriginalFilename().length();
        String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("\\") + 1, filesize);
        //验证文件名是否合格
        if (!ExcelImportUtils.validateExcel(fileName)) {
            return ("文件类型不正确,请上传excel文件");
        }
        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        if (StringUtils.isEmpty(fileName) || size == 0) {
            return ("文件内容不可为空");
        }
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        //批量导入
        String tempUrl = path + "/" + "uploadTemp" + "/";
        System.out.println("______________________________>" + tempUrl);
        File uploadDir = new File(tempUrl);
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists()) uploadDir.mkdirs();
        //新建一个文件
        File tempFile = new File(tempUrl + new Date().getTime() + fileName);
        InputStream is = null;
        try {
            file.transferTo(tempFile);
            is = new FileInputStream(tempFile);
            Workbook workbook = ExcelImportUtils.getWorkbook(is, fileName);
            //删除上传的临时文件
            if (tempFile.exists()) {
                tempFile.delete();
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        long endMili = System.currentTimeMillis();
        System.out.println("excel批量导入总耗时为：" + (endMili - startMili) + "毫秒");
        return ("导入成功");
    }

}
