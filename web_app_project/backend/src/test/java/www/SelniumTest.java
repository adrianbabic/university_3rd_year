package www;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SpringBootTest
public class SeleniumTest {

    @Test
    void testLoginGoodCreds() {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Chrome Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://4ivliy722g3exbyg.myfritz.net:8881/login");

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("mia@gmail.com");

        element = driver.findElement(By.name("password"));
        element.sendKeys("mia123");

        driver.findElement(By.className("btn-primary")).click();

        String rediURL = driver.getCurrentUrl();

        if(rediURL.equals("http://4ivliy722g3exbyg.myfritz.net:8881/instruktori")){
            System.out.println("Super je proslo");
        }else{
            System.out.println("Nije dobro stari");
        }

        driver.quit();
    }

    @Test
    void testStavljanjeFavorita() {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Chrome Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://4ivliy722g3exbyg.myfritz.net:8881/login");

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("mia@gmail.com");

        element = driver.findElement(By.name("password"));
        element.sendKeys("mia123");

        driver.findElement(By.className("btn-primary")).click();

        String rediURL = driver.getCurrentUrl();
        //ulogirali smo se

        driver.findElement(By.className("ButtonInstruktor")).click();

        driver.quit();
    }
}