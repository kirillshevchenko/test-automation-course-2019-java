import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class homeWork1 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void openBrowser(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void homeWorkTest1(){
        driver.get("http://demo.litecart.net/admin/");
        WebElement submitButton = driver.findElement(By.cssSelector("#box-login > form > div.footer > button"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".name")));

        for (int i = 0; i < getMenuItems().size(); i++) {
            getMenuItems().get(i).click();
            Assert.assertTrue("Header is absent", isHeaderPresent());
            if (getSubMenuItems().size() > 0) {
                for (int j = 0; j < getSubMenuItems().size(); j++) {
                    getSubMenuItems().get(j).click();
                    Assert.assertTrue("Header is absent", isHeaderPresent());
                }
            }
        }
    }


    private boolean isHeaderPresent() {
        return driver.findElements((By.cssSelector(".panel-heading"))).size() > 0;
    }

    private List<WebElement> getMenuItems() {
        return driver.findElements(By.cssSelector("li.app"));
    }

    private List<WebElement> getSubMenuItems() {
        return driver.findElements(By.cssSelector("li.doc"));
    }

}
