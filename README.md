---------------------------------------------------------------------------------------------------------
	DigitalRiver Assignment
---------------------------------------------------------------------------------------------------------
Project framework is a hybrid framework.
	- data driven
	- keyword driven
	- page object model
	- testNG
	
Each scenario has the following
1. a separate class.
   i.e. org.openqa.selenium.WebDriver.VerifyPhoneNumberTests
2. a separate xls.
   i.e. data/VerifyPhoneNumberTests.xls
   
All scenarios have a comman Data.xml
   i.e. src/main/resources/xml/Data.xml supported with a src/main/resources/xsd/DataSet.xsd
When we need to add a new dataset for a specific existing test scenario, add it to Data.xml
When we want to dataset for new test scenario, we need to add new complextype in DataSet.xsd and run "mvn clean install".
This will create classes for each complextype. 
i.e. src/test/generated
Then add data to the Data.xml

All the pages related in the test scenario have a separate class. (page object model)

Each testcase in test scenario class file is exhibited as follows:
e.g.
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


---------------------------------------------------------------------------------------------------------
	Pre-Requisites for setup:
---------------------------------------------------------------------------------------------------------
1. Download project from repository url and extract it.
2. Check if maven is installed on your machine. If not installed refer following link for installation.
https://gist.github.com/suyogsakegaonkar/da035c8f070d1f7eace6

3. Check if install java is installed. If not, install Java 1.8.0_45.
Set environmental variable, refer following link.
https://gist.github.com/suyogsakegaonkar/1c55d443c76c3005214d

4. Recommended editor - Eclipse
5. Install maven plugin, if not pre-installed in eclipse.
6. All the dependant libraries are added to pom.xml. No dependant jar required to download.

---------------------------------------------------------------------------------------------------------
	How to run test?
---------------------------------------------------------------------------------------------------------
1. Import extracted project to eclipse, as existing maven project.
2. Goto working directory, where you find the pom.xml
3. Run "mvn install -Dmaven.test.skip=true" to download all the dependant libraries.
4. Then run "mvn clean install" to run the test.
5. View html report at 
	target/surefire-reports/index.html
---------------------------------------------------------------------------------------------------------