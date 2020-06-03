# SeleniumAutomationProject

This is a Hybrid Framework based on Data driven approach using Page Object Model Design Pattern  and TestNG Framework for assertion and writing test cases.
It is a Maven project so basically the skeleton is like:
      src/main/java
        package/packages..
          class/classes..
      src/test/java
        package/packages...
          class/classes..
 Created on separate souce folder
      src/main/resources
 To store the testNg.xml files, In the project we have two xml files one for smoke cases and other one for regression cases.
 pom.xml is there which is the heart of Maven and where all the dependency will exists.
  
 src/main/java comprises:
  1. Config file - It will have the username, password, url and browser on which to run the application
  2. Page classes - It has all the .java classes for each and every page of the application like LoginPage.java, HomePage.java etc
  3. TestBase class - consists of reusable code like loading the properties file, launching the browser, implicitWait, pageLoadTime,          maximize the window,delete all cookies, close and quit the browser windows.
  4. Retry Mechanism - It consists of two classes 
                        a) MyTransformer - to run the retry class (it is based on reflection Api)
                        b) RetryAnalyzer - the class which have the code to re run the fail test cases and also the number of times to                                              re-run.
  5. Util - util methods for Waits,Select Class, Taking Screenshot . iterating rows of the excel file
  6. TestData Excel file - it uses Apache POI libarary
  7. Constants - different constants of the application under test
  8. ExtentReportListener
  
  src/test/java comprises:
  1. Test classes - It will have test classes for each and every page of the application like LoginPageTest.java, HomePageTest.java. It                     is based on TestNG framework which will different annotations and features of testng.
      
