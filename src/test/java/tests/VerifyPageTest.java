package tests;
import base.MainTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SignupPage;
import pages.VerifyPage;
import utils.CsvReader;
import com.opencsv.exceptions.CsvException;
import static java.lang.Thread.sleep;
import java.io.IOException;


public class VerifyPageTest extends MainTest {
    @Test(dataProvider = "valid-verify-page-data")
    public void testVerifyPage(String email, String password, String username, String expectedText) throws InterruptedException {

            HomePage signUpPage = new HomePage();
            signUpPage.clickSignUpButton();

            SignupPage SignupPage = new SignupPage();

            SignupPage.setEmail(email);
            SignupPage.clickButton();

            SignupPage.setPassword(password);
            SignupPage.clickButtonPassword();

            SignupPage.setUsername(username);
            SignupPage.clickContinueButtonForId();

            SignupPage.clickCheckbox();
            sleep(5000);
            SignupPage.clickContinueCheckboxButton();




            VerifyPage VerifyPage = new VerifyPage();

            VerifyPage.switchToParentIframe();
            VerifyPage.switchToChildIframe();
            VerifyPage.switchToSecondChildIframe();


            VerifyPage.clickVerifyButton();

            SoftAssert softAssert = new SoftAssert();


            softAssert.assertTrue(VerifyPage.isMatchGameDisplayed(), "Match game is not displayed.");
            softAssert.assertTrue(VerifyPage.isSubmitButtonDisplayed(), "Submit button is not displayed.");
            softAssert.assertTrue(VerifyPage.isAudioButtonDisplayed(), "Audio button is not displayed.");

            VerifyPage.switchToDefaultContent();

            softAssert.assertAll();
            softAssert.assertEquals(VerifyPage.getVerifyPageText(), expectedText, "Verification text does not match.");
        }

        @DataProvider(name = "valid-verify-page-data")
        public static Object[][] validVerifyDataProvider() throws IOException, CsvException {
            return CsvReader.readCsvFile("src/test/resources/valid-verify-page-data.csv");
        }

    }
