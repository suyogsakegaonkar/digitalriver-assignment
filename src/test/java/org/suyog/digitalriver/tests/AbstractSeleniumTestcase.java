package org.suyog.digitalriver.tests;

import java.util.HashMap;

import org.suyog.digitalriver.helper.ExcelReader;

public abstract class AbstractSeleniumTestcase {

  public static HashMap<String, String> excelData = new HashMap<String, String>();
  ExcelReader excelReader = new ExcelReader();
  
  
  public abstract String getExcelPath();
  
  
}
