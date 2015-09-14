package org.suyog.digitalriver.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.suyog.digitalriver.generated.CountryDetails;
import org.testng.Assert;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

import org.suyog.digitalriver.helper.*;

public class DRCustomerSupportPage extends AbstractPage {

  public DRCustomerSupportPage(HashMap<String, String> excelData, WebDriver driver)
      throws Exception {
    super(excelData, driver);
  }

  public DRCustomerSupportPage waitOnDRCustomerSupportPage() {
    try {
      driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      return new DRCustomerSupportPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public DRCustomerSupportPage readNumberAndVerifyCountryCodeForNumber() {
    try {
      CountryDetails countryDetails = XMLDeserializer.getPageData("countryDetails");
      String phoneNumberStr = readNumber(countryDetails);
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberStr, countryDetails.getIsoCode());
      // System.out.println(phoneNumber.getCountryCode());
      // System.out.println(Integer.parseInt(countryDetails.getCallingCode()));
      Assert.assertEquals(phoneNumber.getCountryCode(),
          Integer.parseInt(countryDetails.getCallingCode()));
      return new DRCustomerSupportPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public DRCustomerSupportPage readNumberAndVerifyIsValidNumber() {
    try {
      CountryDetails countryDetails = XMLDeserializer.getPageData("countryDetails");
      String phoneNumberStr = readNumber(countryDetails);
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberStr, countryDetails.getIsoCode());
      // System.out.println(phoneUtil.isValidNumber(phoneNumber));
      // System.out.println(true);
      Assert.assertEquals(phoneUtil.isValidNumber(phoneNumber), true);
      return new DRCustomerSupportPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public DRCustomerSupportPage readNumberAndVerifyIsPossibleNumber() {
    try {
      CountryDetails countryDetails = XMLDeserializer.getPageData("countryDetails");
      String phoneNumberStr = readNumber(countryDetails);
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberStr, countryDetails.getIsoCode());
      // System.out.println(phoneUtil.isPossibleNumber(phoneNumber));
      // System.out.println(true);
      Assert.assertEquals(phoneUtil.isPossibleNumberWithReason(phoneNumber), ValidationResult.IS_POSSIBLE);
      return new DRCustomerSupportPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public DRCustomerSupportPage readNumberAndVerifyOfflineGeocoderLocation() {
    try {
      CountryDetails countryDetails = XMLDeserializer.getPageData("countryDetails");
      String phoneNumberStr = readNumber(countryDetails);
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberStr, countryDetails.getIsoCode());
      PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();
      // System.out.println(geocoder.getDescriptionForNumber(phoneNumber, Locale.ENGLISH));
      // System.out.println(countryDetails.getCountryName());
      Assert.assertEquals(geocoder.getDescriptionForNumber(phoneNumber, Locale.ENGLISH),
          countryDetails.getCountryName());
      return new DRCustomerSupportPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public DRCustomerSupportPage readNumberAndVerifyNumberType() {
    try {
      CountryDetails countryDetails = XMLDeserializer.getPageData("countryDetails");
      String phoneNumberStr = readNumber(countryDetails);
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberStr, countryDetails.getIsoCode());
      List<String> numberTypes = new ArrayList<String>();
      numberTypes.add("FIXED_LINE");
      numberTypes.add("MOBILE");
      numberTypes.add("TOLL_FREE");
      numberTypes.add("PREMIUM_RATE");
      numberTypes.add("SHARED_COST");
      numberTypes.add("VOIP");
      numberTypes.add("PERSONAL_NUMBER");
      // System.out.println(numberTypes.contains(phoneUtil.getNumberType(phoneNumber).toString()));
      // System.out.println(true);
      Assert.assertEquals(numberTypes.contains(phoneUtil.getNumberType(phoneNumber).toString()),
          true);
      return new DRCustomerSupportPage(excelData, driver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private String readNumber(CountryDetails countryDetails) {
    try {
      int noOfCountries = driver.findElements(By.xpath("//table/tbody/tr")).size();
      String phoneNumberStr = null;
      boolean countryfound = false;
      for (int i = 1; i <= noOfCountries; i++) {
        if (driver.findElement(By.xpath("//table/tbody/tr[" + Integer.toString(i) + "]/td[2]"))
            .getText().trim().equals(countryDetails.getCountryName())) {
          phoneNumberStr =
              driver.findElement(By.xpath("//table/tbody/tr[" + Integer.toString(i) + "]/td[6]"))
                  .getText().trim();
          countryfound = true;
        }
      }
      if (countryfound == false)
        throw new Exception("Phone number not found ...");

      return phoneNumberStr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


}
