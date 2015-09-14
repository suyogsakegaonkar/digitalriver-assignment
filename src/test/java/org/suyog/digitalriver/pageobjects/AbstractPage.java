package org.suyog.digitalriver.pageobjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

  public WebDriver driver;
  HashMap<String, String> excelData;

  public AbstractPage(HashMap<String, String> excelData, WebDriver driver) throws Exception {
    this.excelData = excelData;
    this.driver = driver;
  }

}
