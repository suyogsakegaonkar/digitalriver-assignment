package org.suyog.digitalriver.helper;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReader {

  /*
   * getExcelTestData method reads the excelsheet provided for the specific testcases,
   * and returns a hashmap for specific dataSetId.
   * Refer data/VerifyPhoneNumberTests.xls for sample.
   */
  public HashMap<String, String> getExcelTestData(String dataSetId, String excelFilePath) {
    try {
      int testCaseColumn = -1;
      FileInputStream file = new FileInputStream(new File(excelFilePath));
      HSSFWorkbook workbook = new HSSFWorkbook(file);
      HSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();
      Row row = rowIterator.next();
      Iterator<Cell> cellIterator = row.cellIterator();
      while (cellIterator.hasNext()) {
        Cell cell = cellIterator.next();
        cell.setCellType(Cell.CELL_TYPE_STRING);
        if (cell.getStringCellValue().equals(dataSetId)) {
          testCaseColumn = cell.getColumnIndex();
          break;
        }
      }
      if (testCaseColumn == -1)
        throw new Exception("Test Scenario not found !!!");
      HashMap<String, String> hm = new HashMap<String, String>();
      Iterator<Row> rowIterator1 = sheet.iterator();
      while (rowIterator1.hasNext()) {
        Row row1 = rowIterator1.next();
        Cell cell1 = row1.getCell(0);
        cell1.setCellType(Cell.CELL_TYPE_STRING);
        String aa = cell1.getStringCellValue();

        Cell cell2 = row1.getCell(testCaseColumn);
        cell2.setCellType(Cell.CELL_TYPE_STRING);
        String bb = cell2.getStringCellValue();
        hm.put(aa, bb);
      }
      // for (Map.Entry m : hm.entrySet()) {
      // System.out.println(m.getKey() + " " + m.getValue());
      // }
      file.close();
      return hm;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
