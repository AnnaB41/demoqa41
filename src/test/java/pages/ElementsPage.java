package pages;

import org.openqa.selenium.By;

public class ElementsPage extends BasePage{

    By btnOpenTextBox = By.cssSelector("#item-0"); // нашли элемент по id, использую CSS
    public void openTextBoxPage() {
       clickBase(btnOpenTextBox);
    }
}
