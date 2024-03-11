package pages;

import config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {

    protected WebElement findElementBase(By locator){  // возвращает только 1 элемент

        return ConfigManager.getDriver().findElement(locator);
    }

    protected List<WebElement> findElementsBase(By locator){  // возвращает список элементов, если не найдем,
        // то вернется лист с размером 0. Удобно использовать, чтобы не было ошибки
        return ConfigManager.getDriver().findElements(locator);
    }

    protected void sendTextBase(By locator, String text){
        WebElement element = findElementBase(locator);
        scrollToElement(ConfigManager.getDriver(),element); // скроллим
        element.click(); //кликаем
        element.clear();//очищаем
        element.sendKeys(text);//передаем текст
    }

    private void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clickBase(By locator){
        findElementBase(locator).click();
}

protected void jsExecutorBase(String str){
    JavascriptExecutor js = (JavascriptExecutor) ConfigManager.getDriver();
    js.executeScript(str);
}
    protected String getTextBase(By locator){

        return findElementBase(locator).getText().toUpperCase().trim();
    }

    protected String getTextBaseByElement(WebElement element){
        return element.getText();
    }

    protected boolean isTextEqual(By locator, String expectedResult){

        expectedResult = expectedResult.toUpperCase().trim();
        String actualResult = getTextBase(locator);
        return isTextEqualBy2Strings(actualResult, expectedResult);

    }

    protected String getCurrentUrlBase(){
        return ConfigManager.getDriver().getCurrentUrl(); // функция для возврата текущего Url
    }

    protected boolean isTextEqualBy2Strings(String actualResult, String expectedResult){

        if(expectedResult.equals(actualResult)){
            return true;
        } else{
            System.out.println("actual result: " + actualResult + " expected result: "+ expectedResult);
            return false;
        }
    }
}
