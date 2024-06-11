package ProjectRalphLauren.pages;

import drivers.DriverManager;
import helpers.PropertiesHelper;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SignInPage {

    private String textWelcomeExpected = "Hien";

    private By noThanksLink = By.xpath("//button[normalize-space()='No Thanks']");

    private By iconSignIn = By.xpath("//a[@title='Login']");

    private By formLogin = By.xpath("//*[@id=\"primary\"]/div[1]/div[1]/div[1]");
    private By titleSignIn = By.xpath("//button[@id='login-tab']");
    private By titleCreateAccount = By.xpath("//button[@id='createlogin-tab']");

    private By inputEmail = By.xpath("(//input[@type='email'][1])[1]"); //*[@id=\"dwfrm_login_username_d0vdsjynhqrg\"]");
    private By inputPassword = By.xpath("(//input[@type='password'][1])[2]");//*[@id=\"dwfrm_login_password_d0ncibcwgjzl\"]"); //
    private By checboxRememberMe = By.xpath("(//input[@name='dwfrm_login_rememberme'])[2]");
    private By buttonSignIn = By.xpath("(//button[@type='submit'])[10]"); //(//button[normalize-space()='Sign In'])[3]");


    private void setEmail(String email) {
        WebUI.sleep(2);
        WebUI.highLightElement(inputEmail);
        WebUI.setText(inputEmail, email);

    }

    private void setPassword(String password) {
        WebUI.sleep(2);
        WebUI.highLightElement(inputPassword);
        WebUI.setText(inputPassword, password);
    }

    private void clickSignInbutton() {
        WebUI.sleep(1);
        WebUI.highLightElement(inputEmail);
        WebUI.clickElement(buttonSignIn);
    }

    public void verifySignInSuccess() {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("pplp/account?fromAccountLogin=true"), "SignIn unsuccessful. Still on the Sign In page.");
        Assert.assertTrue(WebUI.checkElementExist(MyAccountPage.titleWelcomeMyAccount), "Sign In unsuccessful. Title Welcome MyAccount NOT exist.");
        Assert.assertTrue(WebUI.checkElementDisplayed(MyAccountPage.titleWelcomeMyAccount), "Sign In unsuccessful. Title Welcome MyAccount NOT display.");
        Assert.assertTrue(WebUI.checkElementExist(MyAccountPage.SignOutLink), "Sign In unsuccessful. Sign Out link NOT exist.");
        Assert.assertTrue(WebUI.checkElementDisplayed(MyAccountPage.SignOutLink), "Sign In unsuccessful. Sign Out link NOT display.");
        Assert.assertTrue(WebUI.checkElementExist(MyAccountPage.menuTagMyAccount), "Sign In unsuccessful. All tag My Account NOT exist.");
        Assert.assertTrue(WebUI.checkElementDisplayed(MyAccountPage.menuTagMyAccount), "Sign In unsuccessful. All tag My Account NOT display.");
        Assert.assertEquals(WebUI.getElementText(MyAccountPage.nameAccount), textWelcomeExpected, "Sign In unsuccessful. Content welcome text NOT match.");
        System.out.println("\uD83C\uDF3AContent welcome text ACTUAL RESULT is: "
                + WebUI.getElementText(MyAccountPage.titleWelcomeMyAccount)
                + WebUI.getElementText(MyAccountPage.nameAccount));
    }

    public MyAccountPage signInRalphLauren(String email, String password) {
        // WebUI.sleep(2);
        WebUI.opeURL(PropertiesHelper.getValue("url"));
        WebUI.waitForPageLoaded();
        WebUI.clickElement(noThanksLink);
        WebUI.sleep(2);
        WebUI.clickElement(iconSignIn);
        WebUI.sleep(2);
        setEmail(email);
        setPassword(password);
        clickSignInbutton();
        WebUI.sleep(2);
        return new MyAccountPage();
    }

}
