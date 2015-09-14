package org.suyog.digitalriver.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.suyog.digitalriver.pageobjects.GoogleSearchPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.suyog.digitalriver.tests.*;

public class VerifyPhoneNumberTests extends AbstractSeleniumTestcase {

  private String testDataId = "VerifyPhoneNumber_1";
  public WebDriver driver;

  @Override
  public String getExcelPath() {
    return "data/VerifyPhoneNumberTests.xls";
  }

  @BeforeMethod
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
  }

  @Test(description = "Validates the country code for a phone number.")
  public void checkCountryCodeForNumber() throws Exception {
    excelData = excelReader.getExcelTestData(testDataId, getExcelPath());
    GoogleSearchPage googleSearchPage = new GoogleSearchPage(excelData, driver);
    // @formatter:off
    googleSearchPage.gotoGooglePage()
                    .searchOfficeNumber()
                    .verifySearchAndClick()
                    .waitOnDRCustomerSupportPage()
                    .readNumberAndVerifyCountryCodeForNumber();
 // @formatter:on
  }

  @Test(description = "Tests whether a phone number matches a valid pattern.")
  public void checkIsValidNumber() throws Exception {
    excelData = excelReader.getExcelTestData(testDataId, getExcelPath());
    GoogleSearchPage googleSearchPage = new GoogleSearchPage(excelData, driver);
    // @formatter:off
     googleSearchPage.gotoGooglePage()
                     .searchOfficeNumber()
                     .verifySearchAndClick()
                     .waitOnDRCustomerSupportPage()
                     .readNumberAndVerifyIsValidNumber();
  // @formatter:on
  }

  @Test(
      description = "Check whether a phone number is a possible number given a number in the form of a string, and the region where the number could be dialed from.")
  public void checkIsPossibleNumber() throws Exception {
    excelData = excelReader.getExcelTestData(testDataId, getExcelPath());
    GoogleSearchPage googleSearchPage = new GoogleSearchPage(excelData, driver);
    // @formatter:off
     googleSearchPage.gotoGooglePage()
                     .searchOfficeNumber()
                     .verifySearchAndClick()
                     .waitOnDRCustomerSupportPage()
                     .readNumberAndVerifyIsPossibleNumber();
  // @formatter:on
  }

  @Test(description = "Checks the offlibe geocoder location for a phone number.")
  public void checkOfflineGeocoderLocation() throws Exception {

    excelData = excelReader.getExcelTestData(testDataId, getExcelPath());
    GoogleSearchPage googleSearchPage = new GoogleSearchPage(excelData, driver);
    // @formatter:off
     googleSearchPage.gotoGooglePage()
                     .searchOfficeNumber()
                     .verifySearchAndClick()
                     .waitOnDRCustomerSupportPage()
                     .readNumberAndVerifyOfflineGeocoderLocation();
  // @formatter:on
  }

  @Test(description = "Gets the type of a phone number.")
  public void checkNumberType() throws Exception {
    excelData = excelReader.getExcelTestData(testDataId, getExcelPath());
    GoogleSearchPage googleSearchPage = new GoogleSearchPage(excelData, driver);
    // @formatter:off
     googleSearchPage.gotoGooglePage()
                     .searchOfficeNumber()
                     .verifySearchAndClick()
                     .waitOnDRCustomerSupportPage()
                     .readNumberAndVerifyNumberType();
  // @formatter:on
  }

  @AfterMethod
  public void tearDown() throws Exception {
    driver.quit();
  }



}
