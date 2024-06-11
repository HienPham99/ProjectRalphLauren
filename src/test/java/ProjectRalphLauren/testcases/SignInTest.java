package ProjectRalphLauren.testcases;

import ProjectRalphLauren.pages.MyAccountPage;
import ProjectRalphLauren.pages.SignInPage;
import common.BaseTest;
import helpers.ExcelHelper;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {

    SignInPage signInPage;
    MyAccountPage myAccountPage;


    @Test(priority = 1)
    public void testLoginSuccess() {
        signInPage = new SignInPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/DataLogin.xlsx", "Login");
        myAccountPage = signInPage.signInRalphLauren(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1)
        );
        signInPage.verifySignInSuccess();
    }
}
