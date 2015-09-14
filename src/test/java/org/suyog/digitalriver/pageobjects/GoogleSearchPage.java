package org.suyog.digitalriver.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.suyog.digitalriver.generated.GoogleSearch;
import org.suyog.digitalriver.helper.XMLDeserializer;

public class GoogleSearchPage extends AbstractPage {

  public GoogleSearchPage(HashMap<String, String> excelData, WebDriver driver) throws Exception {
    super(excelData, driver);
  }

  public GoogleSearchPage gotoGooglePage() {
    driver.get("http://www.google.com");
    try {
      return new GoogleSearchPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public GoogleSearchPage searchOfficeNumber() {
    try {
      GoogleSearch googleSearch = XMLDeserializer.getPageData("googleSearch");
      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      driver.findElement(By.name("q")).sendKeys(googleSearch.getSearchText());
      driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
      return new GoogleSearchPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public DRCustomerSupportPage verifySearchAndClick() {
    try {
      GoogleSearch googleSearch = XMLDeserializer.getPageData("googleSearch");
      int numberofOption = driver.findElements(By.xpath(".//*[@id='rso']/div[2]/div")).size();
      ArrayList<String> linkDescText =
          new ArrayList(Arrays.asList(googleSearch.getLinkDescText().split(",")));
      boolean linkDescTextstatus = false;
      boolean linkTextstatus = false;
      int i = 1;
      for (; i <= 10; i++) {
        String linkDescTextFromPage =
            driver
                .findElement(
                    By.xpath("//*[@id='rso']/div[2]/div[" + Integer.toString(i)
                        + "]//span[@class='st']")).getText().toLowerCase();
        for (String linkDescTextpart : linkDescText) {
          if (linkDescTextFromPage.contains(linkDescTextpart.toLowerCase())) {
            linkDescTextstatus = true;
          } else {
            linkDescTextstatus = false;
          }
        }
        if (linkDescTextstatus == true) {
          ArrayList<String> linkText =
              new ArrayList(Arrays.asList(googleSearch.getLinkText().split(",")));
          String linkTextFromPage =
              driver
                  .findElement(
                      By.xpath(".//*[@id='rso']/div[2]/div[" + Integer.toString(i) + "]//div/cite"))
                  .getText().toLowerCase();
          for (String linkTextpart : linkText) {
            if (linkTextFromPage.contains(linkTextpart.toLowerCase())) {
              linkTextstatus = true;
            } else {
              linkTextstatus = false;
            }
          }
        }
        if (linkTextstatus == true)
          break;
      }
      if (linkTextstatus == true)
        driver
            .findElement(By.xpath("//*[@id='rso']/div[2]/div[" + Integer.toString(i) + "]//h3/a"))
            .click();
      else
        throw new Exception("No search result found ...");
      return new DRCustomerSupportPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
