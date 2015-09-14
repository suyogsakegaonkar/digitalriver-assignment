package org.suyog.digitalriver.helper;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.suyog.digitalriver.generated.PageData;
import org.suyog.digitalriver.tests.*;

public class XMLDeserializer {

/*
 * getPageData method returns a object for specified datatype i.e. key
 * It reads from the xml (src/main/resources/xml/Data.xml) and returns a data object.
 */
  public static <T> T getPageData(String key) throws Exception {
    if (AbstractSeleniumTestcase.excelData == null)
      throw new Exception("Excel Data is empty !!!");
    else {
      String value = AbstractSeleniumTestcase.excelData.get(key);
      JAXBContext jaxbContext = JAXBContext.newInstance("org.suyog.digitalriver.generated");
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      PageData pageData =
          (PageData) unmarshaller.unmarshal(new FileInputStream("src/main/resources/xml/Data.xml"));
//      int a = pageData.getClass().getDeclaredFields().length;
      Method[] methodsList = pageData.getClass().getMethods();
      ArrayList<Object> objectArray = new ArrayList<Object>();
      for (Method mtd : methodsList) {
        objectArray = (ArrayList<Object>) mtd.invoke(pageData, null);
        for (Object tempObj : objectArray) {
          if (tempObj.getClass().getSimpleName()
              .equals(key.substring(0, 1).toUpperCase() + key.substring(1))) {
            Method[] methodsFilteredList = tempObj.getClass().getMethods();
            for (Method methodsFiltered : methodsFilteredList) {
              if (methodsFiltered.getName().equals("getId"))
                if (methodsFiltered.invoke(tempObj, null).equals(value)) {
                  return (T) tempObj;
                }
            }
          }
        }
      }
      return null;
    }
  }

}
