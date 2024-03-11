package tests;

import config.ConfigManager;
import dto.TextBoxUserInfo;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TextBoxTests extends BaseTest{

    @BeforeClass // перед любым тестом сначала будем переходить по страницам предварительным
    public void beforeClass(){
        ConfigManager.navigateToMainPage();
        mainPage.openElementsPage();
        elementsPage.openTextBoxPage();
    }

    @Test
    public void textBoxPageOpened()  {
   //     Thread.sleep(3000);
        Assert.assertTrue(textBoxPage.validateUrlTextBoxCorrect());
    }

    @Test(description = "test with filling all fields in the form and validate the common result")
    public void testForm(){
        TextBoxUserInfo textBoxUserInfo = new TextBoxUserInfo()
                .withName("John")
                .withEmail("john@mail.com")
                .withCurrentAddress("first street")
                .withPermanentAddress("second street");
        textBoxPage.fillForm(textBoxUserInfo);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        Assert.assertTrue(textBoxPage.validateUserInfoDisplaysCorrect(textBoxUserInfo));


    }
}
