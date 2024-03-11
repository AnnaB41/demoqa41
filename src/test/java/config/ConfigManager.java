package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class ConfigManager { // on the lessons: BaseTest.java

    public static WebDriver driver;



    public static WebDriver getDriver() { // даем время подождать
//        int counter = 0;
//        while (driver == null || counter <= 5){
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

        //singleton
//        if(driver == null) {
//            setUp("chrome");
//        }
        return driver;
    }

   @BeforeSuite
    @Parameters("browser")
    public static void setUp(@Optional("chrome") String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--lang=en");
//            chromeOptions.addArguments("--headless");  браузер не открывается, когда запускаем
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addPreference("intl.accept_languages", "en");
//            firefoxOptions.addArguments("-headless");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(firefoxOptions);
        }
        else if(browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setCapability("language", "en");
//            edgeOptions.addArguments("--headless");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);
        }
        else if(browser.equalsIgnoreCase("safari")) {
            SafariOptions safariOptions = new SafariOptions();
            safariOptions.setCapability("language", "en");
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver(safariOptions);
        }
        else {
            throw new IllegalArgumentException("Ivalid browser name: " + browser);
        }
        driver.manage().window().maximize(); // открываем окно по максимуму
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20)); // ожидалка загрузки страницы до 20 сек (это максимум)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // когда ищем элемент файнд, ждем до 60 сек
        // если он появляется То берем, ждем до 60 секунд
     //   driver.navigate().to("https://demoqa.com/");  // открываем страницу
       navigateToMainPage();
    }

    public static void navigateToMainPage(){
        driver.navigate().to("https://demoqa.com/");
    }

    @AfterSuite
    public static void tearDown() {

        driver.quit(); // закрывает браузер и подчищает кукис
    }
}