import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebPageTest {

    private static WebDriver driver = null;

    public WebPageTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.gecko.driver","C:/Users/saure/OneDrive/Codes/GeckoDriver/geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void testLogin() throws InterruptedException {

        // Go to LocalHost http://localhost:8080/#!login
        driver.get("http://localhost:8080/#!login");
        Thread.sleep(2000); //Thread.sleep für bessere Beobachtung

        // Maximize the page
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Select Login Textfield and enter "testcase@selenium.de"
        driver.findElement(By.xpath("//*[@id=\"gwt-uid-3\"]")).sendKeys("testcase@selenium.de");
        Thread.sleep(2000);

        // Select Password PasswordField and enter "1234xyz98"
        driver.findElement(By.xpath("//*[@id=\"gwt-uid-5\"]")).sendKeys("1234xyz98");
        Thread.sleep(2000);

        // Press Button "Login"
        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div/div/div[2]/div/div[5]/div")).click();
        Thread.sleep(3000);

    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

}
