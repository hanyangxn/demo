package com.hy.demo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

/**
 * EXCEL工具类
 * @author Administrator
 *
 */
public class ExcelImportUtils {  
	 private final static String EXCEL_2003 =".xls";    //2003- 版本的excel
     private final static String EXCEL_2007 =".xlsx";   //2007+ 版本的excel
    
    // @描述：是否是2003的excel，返回true是2003   
    public static boolean isExcel2003(String filePath)  {    
        return filePath.matches("^.+\\.(?i)(xls)$");    
    }    
     
    //@描述：是否是2007的excel，返回true是2007   
    public static boolean isExcel2007(String filePath)  {    
        return filePath.matches("^.+\\.(?i)(xlsx)$");    
    }    
      
  /** 
   * 验证EXCEL文件 
   * @param filePath 
   * @return 
   */  
  public static boolean validateExcel(String filePath){  
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){    
            return false;    
        }    
        return true;  
  }  
  
  
  /**
   * 描述：根据文件后缀，自适应上传文件的版本 
   * @param inStr,fileName
   * @return
   * @throws Exception
   */
  public  static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
      Workbook wb = null;
      String fileType = fileName.substring(fileName.lastIndexOf("."));
      if(EXCEL_2003.equals(fileType)){
          wb = new HSSFWorkbook(inStr);  //2003-
      }else if(EXCEL_2007.equals(fileType)){
          wb = new XSSFWorkbook(inStr);  //2007+
      }else{
          throw new Exception("解析的文件格式有误！");
      }
      return wb;
  }
} 