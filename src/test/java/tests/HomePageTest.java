package tests;

import base.MainTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;

import static org.testng.AssertJUnit.assertTrue;

@Epic("Signup")
public class HomePageTest extends MainTest {
    @Test
    @Description("Test for verifying the signup process")
    @Feature("Signup Feature")
    public void testSingUp() {
       HomePage HomePage = new HomePage();

        HomePage.clickSignUpButton();

        SignupPage SignupPage = new SignupPage();
        assertTrue(SignupPage.isRegistrationFormDisplayed());

    }
}
