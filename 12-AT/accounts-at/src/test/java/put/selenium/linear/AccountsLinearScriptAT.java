package put.selenium.linear;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import put.selenium.utils.ScreenshotAndQuitOnFailureRule;

import static org.junit.Assert.*;


public class AccountsLinearScriptAT {

    private WebDriver driver;

    @Rule
    public ScreenshotAndQuitOnFailureRule screenshotOnFailureAndWebDriverQuitRule =
            new ScreenshotAndQuitOnFailureRule();


    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        this.driver = new ChromeDriver();
        screenshotOnFailureAndWebDriverQuitRule.setWebDriver(driver);
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
    }

    @Test
    public void successfulUserRegistration() throws Exception {
        driver.get("http://localhost:8080/accounts/controller?action=db_reset");
        driver.manage().window().setSize(new Dimension(658, 672));
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("martagrimaldos");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("repeat_password")).sendKeys("12345678");
        driver.findElement(By.name("name")).sendKeys("marta");
        driver.findElement(By.name("addressData")).sendKeys("alicante");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("martagrimaldos");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.name("submitAdd")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).click();
        vars.put("account_number", driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).getText());
        driver.findElement(By.name("accountDeposit")).click();
        {
        WebElement dropdown = driver.findElement(By.name("accountDeposit"));
        dropdown.findElement(By.xpath("//option[. = 'vars.get("account_number").toString()']")).click();
        }
        driver.findElement(By.name("amountDeposit")).click();
        driver.findElement(By.name("amountDeposit")).sendKeys("100");
        driver.findElement(By.name("submitDeposit")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), is("99,00"));
        driver.findElement(By.name("accountPayUp")).click();
        {
        WebElement dropdown = driver.findElement(By.name("accountPayUp"));
        dropdown.findElement(By.xpath("//option[. = 'vars.get("account_number").toString()']")).click();
        }
        driver.findElement(By.name("amountPayUp")).click();
        driver.findElement(By.name("amountPayUp")).sendKeys("50");
        driver.findElement(By.name("submitPayUp")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).click();
        assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText(), is("48,00"));
        }


}
