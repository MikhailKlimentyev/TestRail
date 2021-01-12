package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropDown {

    WebDriver driver;
    String label;
    String locator = "//label[contains(text(),'%s')]/ancestor::td//a";
    String optionLocator = "//li[contains(text(),'%s')]";

    public DropDown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        driver.findElement(By.xpath(String.format(locator, label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();
    }
}
