package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class SignupPage extends BasePage {
    @FindBy(className = "js-continue-container")
    private WebElement registrationForm;

    @FindBy(partialLinkText = "Sign up")
    private WebElement signUpButton;

    @FindBy(id = "email")
    private WebElement emailInputField;

    @FindBy(css = "#email-container .js-continue-button")
    private WebElement emailContinueButton;

    @FindBy(css = "#password")
    private WebElement passwordInputField;

    @FindBy(css = "#password-container .js-continue-button")
    private WebElement passwordContinueButton;

    @FindBy(css = "#login")
    private WebElement usernameField;

    @FindBy(css = "#username-container .js-continue-button")
    private WebElement usernameContinueButton;
    @FindBy(css = "#opt-in-container .js-continue-button")
    private WebElement continueButtonCheckbox;
    @FindBy(css = "#email-err .mb-0")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//*[@id='password-err']/p[1]")
    private WebElement passwordErrorMessage;

    @FindBy(css = "#login-err div.mb-1")
    private WebElement usernameInvalidSymbolErrorMessage;

    @FindBy(css = "#login-err .m-1")
    private WebElement usernameTooLongError;

    @FindBy(className = "password-validity-pill-fail")
    private WebElement passwordErrorValidityIndicatorRed;

    @FindBy(xpath = "//*[@id='password-err']/div/span[2]")
    private WebElement passwordErrorValidityIndicatorYellow;

    @FindBy(xpath = "//*[@id='password-err']/p[2]")
    private WebElement passwordErrorMessageText;

    @FindBy(id = "opt_in")
    private WebElement checkbox;

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

    private static final SecureRandom random = new SecureRandom();

    @Step("Check if the registration form is displayed")
    public boolean isRegistrationFormDisplayed() {
        return registrationForm.isDisplayed();
    }



    @Step("Set email ")
    public void setEmail(String email) {
        waitToBeClickable(emailInputField);
       emailInputField.clear();
       emailInputField.sendKeys(email);
    }

    @Step("Click the continue button for email")
    public void clickButton() {
        waitToBeClickable(emailContinueButton);
        emailContinueButton.click();
    }

    @Step("Click the continue button for password")
    public void clickButtonPassword() {
        waitToBeClickable(passwordContinueButton);
        passwordContinueButton.click();
    }

    @Step("Set password ")
    public void setPassword(String password) {
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
    }

    @Step("Set username")
    public void setUsername(String username) {
        waitToBeClickable(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    @Step("Click the continue button for username")
    public void clickContinueButtonForId() {
        waitToBeClickable(usernameContinueButton);
        usernameContinueButton.click();
    }

    @Step("Get email error message")
    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }


    @Step("Get password error message")
    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }

    @Step("Get username error message")
    public String getUsernameErrorMessageText() {
        return usernameInvalidSymbolErrorMessage.getText();
    }

    @Step("Get username too long error message")
    public String getUsernameTooLongErrorMessage() {
        return usernameTooLongError.getText();

    }
    @Step("Check if password validity indicator (red) is displayed")
    public boolean isPasswordValidityShortDisplayed() {
        return passwordErrorValidityIndicatorRed.isDisplayed();
    }

    @Step("Get password error message text")
    public String getPasswordErrorMessageText() {
        return passwordErrorMessageText.getText();
    }

    @Step("Check if password validity indicator (yellow) is displayed")
    public boolean isPasswordValidityShortDisplayedYellow() {
        return passwordErrorValidityIndicatorYellow.isDisplayed();
    }

    @Step("Click the checkbox")
    public void clickCheckbox() {
        checkbox.click();
    }

    @Step("Check if the checkbox is selected")
    public boolean isCheckboxSelected() {
        return checkbox.isSelected();
    }


    @Step("Click the continue button for the checkbox")
    public void clickContinueCheckboxButton() {
        continueButtonCheckbox.click();
    }
    //Генериране на неправилни пароли + правелен емайл
    @Step("Generate an invalid password")
    public String generateInvalidPassword() {
        int length = 4; // Short password length

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    password.append(LOWER.charAt(random.nextInt(LOWER.length())));
                    break;
                case 1:
                    password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
                    break;
                case 2:
                    password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));
                    break;
            }
        }

        return password.toString();
    }
    @Step("Export email and password pairs to CSV file ")
    public void exportEmailPasswordPairsToCSV(int numPairs, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Email,InvalidPassword\n");
            for (int i = 0; i < numPairs; i++) {
                String email = generateRandomEmail();
                String password = generateInvalidPassword();
                writer.append(email).append(",").append(password).append("\n");
            }
            System.out.println("Email and password pairs exported to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Step("Generate a random email address")
    private String generateRandomEmail() {
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            email.append(LOWER.charAt(random.nextInt(LOWER.length())));
        }
        email.append("@example.com"); // Use a common domain for simplicity
        return email.toString();
    }
}
