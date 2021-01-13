# TestRail
---

Solution which allows linking test cases in TestRail system with autotests 
by assigning @TestRail annotation with initialized annotation field by test case id to test method.
As a result autotests run runs test cases and creates Test Runs & Results statistics in TestRail system.

---
### Preconditions:
* Create test account. [Click on the link in order to create free 14-day TestRail trial as a test account](https://secure.gurock.com/customers/testrail/trial?utm_source=adwords&utm_medium=cpc&utm_campaign=europe_en_brand&utm_content=testrail%20api&creative=440375667010&keyword=testrail%20api&matchtype=e&network=g&device=c&gclid=Cj0KCQiA6Or_BRC_ARIsAPzuer_X18j6M6DPw8Rw4AOYspkEsX-rAG4dyLsLN0yZ_Jf2wj5g55oRc84aAk9fEALw_wcB)
* Under created test account create project with the name 'TestRail' in TestRail system
* Under 'TestRail' project create test cases 
* Annotate appropriate test method with @TestRail annotation
* Initialize @TestRail annotation field with number of test case from 'TestRail' project 
---

Logic for retrieving testCaseID from @TestRail annotation assigned to test method implemented in TestListener class.
Also there implemented logic for checking if project with 'TestRail' name exists in TestRail system before tests' run.
Logic which allows setting pass or fail status in test case run in TestRail system implemented 
in onTestSuccess() and onTestFailure() methods respectively.
Calls of REST endpoints via REST Assured are used for logic implementation.

---
### AUT
https://www.gurock.com/testrail/
### API
https://www.gurock.com/testrail/docs/api
### Stack
Java 8, Selenium WebDriver, REST Assured, TestNG, Maven, Allure, Lombok, Log4j2, GSON
### Run tests
``` mvn clean -DsuiteXmlFile=src/test/resources/smoketest.xml install ```