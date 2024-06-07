package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerifyPage extends BasePage {
    @FindBy(xpath = "//*[@id='captcha-and-submit-container']/div[1]")
    private WebElement verifyPageElement;

    @FindBy(xpath = "//button[contains(text(),'Verify')]")
    private WebElement verifyButton;

    @FindBy(css = ".match-game")
    private WebElement matchGame;

    @FindBy(css = "button.sc-nkuzb1-0.yuVdl")
    private WebElement submitButton;

    @FindBy(css = ".audio-button")
    private WebElement audioButton;


    @FindBy(css = "iframe[title='Please verify by completing this captcha.']")
    WebElement verificationParentIframe;

    @FindBy(css = "iframe[title='Verification challenge']")
    WebElement verificationChildFirstIframe;

    @FindBy(css = "#game-core-frame")
    WebElement verificationChildSecondIframe;




    @Step("Get verify page text")
    public String getVerifyPageText() {
        return verifyPageElement.getText();

    }

    @Step("Click verify button")
    public void clickVerifyButton() {
        waitToBeClickable(verifyButton);
        verifyButton.click();
    }

    @Step("Check if match game is displayed")
    public boolean isMatchGameDisplayed() {
        return matchGame.isDisplayed();
    }

    @Step("Check if submit button is displayed")
    public boolean isSubmitButtonDisplayed() {
        return submitButton.isDisplayed();
    }

    @Step("Check if audio button is displayed")
    public boolean isAudioButtonDisplayed() {
        return audioButton.isDisplayed();
    }

    @Step("Switch to verification iframe Parent")
    public void switchToParentIframe() {
        switchToIframeWhenItReady(verificationParentIframe);
    }

    @Step("Switch to first child iframe")
    public void switchToChildIframe() {
        switchToIframeWhenItReady(verificationChildFirstIframe);
    }

    @Step("Switch to second child iframe")
    public void switchToSecondChildIframe() {
        switchToIframeWhenItReady(verificationChildSecondIframe);
    }
}
