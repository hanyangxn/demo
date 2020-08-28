package com.hy.demo.controller;

import com.hy.demo.domain.Person;
import com.hy.demo.emun.PositionType;
import com.hy.demo.exception.ManagementCockpitException;
import com.hy.demo.service.impl.ExcelService;
import com.hy.demo.util.*;
import com.hy.demo.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


@RestController
public class PersonController {
    final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private PersonService personService;

    @Autowired
    private ExcelService excelService;

    @RequestMapping("/person")
    public ResultDto<PagedResult<Person>> test(Integer pageNo, Integer pageSize) {
        return personService.selectList(pageNo, pageSize);
    }


    @RequestMapping("/download")
    public void downloadKB(HttpServletRequest request, HttpServletResponse response) {
        try {
            excelService.test(request, response);
        } catch (ManagementCockpitException e) {
            logger.error(DateUtil.now("yyyy-MM-dd HH:mm:ss.SSS") + " - Warning of " +
                    Thread.currentThread().getStackTrace()[1].getMethodName() + ": " + e.getMessage());
        }
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
        String originalFilename = file.getOriginalFilename();
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
