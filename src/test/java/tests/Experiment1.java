package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Experiment1 {
@Test
    public static void switchTab(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/browser-windows");
        driver.manage().window().maximize();

        String mainWindowHandler = driver.getWindowHandle(); // Получает идентификатор текущего (главного) окна браузера и сохраняет его в переменной mainWindowHandler.
        WebElement newButton = driver.findElement(By.xpath("//button[@id='tabButton']")); //Находим кнопку
        newButton.click(); // и жмем на нее, чтобы открыть новую вкладку.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));// Создает объект WebDriverWait, который будет ожидать на протяжении 5 секунд, чтобы выполнить следующую команду.
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // Ожидает, пока количество открытых окон в браузере не станет равным 2.
        Set<String> allWindowHandles = driver.getWindowHandles();// Получает все идентификаторы окон, открытых в браузере, и сохраняет их в коллекции типа Set<String>.

        String newWindowHandler = ""; // Объявляет переменную newWindowHandler, которая будет использоваться для хранения идентификатора нового окна или вкладки.
        for (String windowHandle : allWindowHandles){ // Пробегаемся по всем идентификаторам окон, найденным на предыдущем шаге.
            if(!windowHandle.equals(mainWindowHandler)){ // Проверяем, является ли текущий идентификатор окна отличным от идентификатора главного окна, сохраненного на первом шаге.
                newWindowHandler = windowHandle;// Если идентификатор окна отличается от главного, сохраняем этот идентификатор в переменной newWindowHandler и завершаем цикл.
                break;

            }
        }
        driver.switchTo().window(newWindowHandler); // Переключаемся на новую вкладку, используя его идентификатор.
        WebElement newPageElement = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));//  пытаемся найти элемент на новой вкладке.
        newPageElement.click();
        driver.quit();

    }
@Test
    public static void switchTabByIndex(){ // 2-рой способ получения хэндлс
        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/browser-windows");
        driver.manage().window().maximize();


        WebElement newButton = driver.findElement(By.xpath("//button[@id='tabButton']")); //Находим кнопку
        newButton.click(); // и жмем на нее, чтобы открыть новую вкладку.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));// Создает объект WebDriverWait, который будет ожидать на протяжении 5 секунд, чтобы выполнить следующую команду.
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); // Ожидает, пока количество открытых окон в браузере не станет равным 2.

        Set<String> handles = driver.getWindowHandles();
        driver.switchTo().window(handles.toArray()[1].toString());

        WebElement newPageElement = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));//  пытаемся найти элемент на новой вкладке.
        newPageElement.click();
        driver.quit();

    }

    @Test
    public void testRectangle() throws InterruptedException {

        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/slider");
        driver.manage().window().maximize();

        WebElement range = driver.findElement(By.xpath("//input[@type='range']"));

        Rectangle rectangleRange = range.getRect(); // получить прямоугольник элемента
        int widthRange = rectangleRange.width;  // получить ширину прямоугольника
        System.out.println("with = "+ widthRange);

        Actions actions = new Actions(driver);

        actions.moveToElement(range).perform();  // попадаем на левый верхний угол

        int xLocation = range.getLocation().getX();  // получаем нашу локацию по оси х
        System.out.println("x location " + xLocation);

      //  int xTo = (widthRange - xLocation)/2;  // вычисляем, насколько нам нужно подвинуть
      int xTo2 = (widthRange)/3;  // вычисляем, насколько нам нужно подвинуть


        actions.moveByOffset(xTo2, 0).click().perform();  // показываем, куда передвинуть точку

        Thread.sleep(5000);

        driver.quit();



       }

       @Test
       public void iframeTest() throws InterruptedException {
            WebDriver driver = new FirefoxDriver();
            driver.get("https://the-internet.herokuapp.com/iframe");
           driver.manage().window().maximize();

           Thread.sleep(2000);

           By textElementInIframe = By.xpath("//body[@id='tinymce']/p"); // локатор для элемента


           List<WebElement> beforeIframe = driver.findElements(textElementInIframe); // т.к. элемент находится
           // внутри айфрейма, то возвращается 0 элементов
           System.out.println("before switch to iframe should be zero " + beforeIframe.size());

         //  driver.switchTo().frame(0); //переключились на айфрейм, 0 это означает 1 айфрейм, как в массивах
           driver.switchTo().frame("mce_0_ifr"); // переключаемся на айфрейм по id

           List<WebElement> insideIframe = driver.findElements(textElementInIframe);
           System.out.println("after switch to iframe should be one " + insideIframe.size());

           driver.switchTo().defaultContent(); // ушли из айфрейм

           List<WebElement> backToDefault = driver.findElements(textElementInIframe);
           System.out.println("after switch back to default should be one " + backToDefault.size());

           driver.quit();
       }

       @Test
       public void iframeNestedTest() throws InterruptedException {

           WebDriver driver = new FirefoxDriver();
           driver.get("https://the-internet.herokuapp.com/nested_frames");
           driver.manage().window().maximize();

           Thread.sleep(2000);

           By bodyTag = By.xpath("//body");
           System.out.println("not a frame, size 0: " + driver.findElements(bodyTag).size());

           driver.switchTo().frame("frame-top");
           driver.switchTo().frame(0);
           System.out.println("LEFT + " + driver.findElement(bodyTag).getText());

           driver.switchTo().defaultContent(); // ушли из айфрейм
           driver.switchTo().defaultContent(); // ушли из айфрейм


           driver.switchTo().frame("frame-bottom");
           System.out.println("bottom + " + driver.findElement(bodyTag).getText());

           driver.quit();



       }



    @Test
    public void softAssertsTest(){ //более мягкие ассерты, продолжают работать даже если один из них упал

        WebDriver driver = new FirefoxDriver();
        driver.get("https://demoqa.com/text-box");
        driver.manage().window().maximize();

        SoftAssert softAssert = new SoftAssert();
        String text = driver.findElement(By.xpath("//label[@id='currentAddress-label']")).getText();
        //Current Address
        softAssert.assertTrue(text.contains("Current"), "current");
        softAssert.assertTrue(text.contains("name"), "name");
        softAssert.assertTrue(text.contains("Address"));
        System.out.println("all tests checked");
        softAssert.assertAll();

        driver.quit();



    }
}
