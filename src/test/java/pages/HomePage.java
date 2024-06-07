package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(partialLinkText = "Sign up")
    private WebElement signUpButton;

    @Step("Click the Sign Up button")
    public void clickSignUpButton() {
        signUpButton.click();
    }
}
