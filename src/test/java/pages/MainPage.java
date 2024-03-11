package pages;

import org.openqa.selenium.By;

public class MainPage extends BasePage{

    By textElements = By.xpath("//h5[text()='Elements']");
    By boxElements = By.xpath("//div[contains(@class, 'avatar')]"); // дополнительный локатор, если первый не работает

    String elements = "Elements";

    String boxElementsJS = "document.querySelector(\".card-body\").click();";

//    public String getTextElements(){
//        return getTextBase(textElements);
//    }

    public boolean validateTextElementsCorrect(){
        return isTextEqual(textElements, elements);
    }


    public void openElementsPage() {
      //  clickBase(boxElements); // или можно textElements
        jsExecutorBase(boxElementsJS);  // другой вариант, у кого предыдущий не открывался
    }
}
