package ProjectRalphLauren.pages;

import org.openqa.selenium.By;

public class MyAccountPage {

    public static By titleWelcomeMyAccount= By.xpath("//*[@id=\"wrapper\"]/div[2]/div/div/div[1]/div[1]"); //*[@id=\"wrapper\"]/div[2]/div/div/div[1]");
    public static By nameAccount = By.xpath("//div[normalize-space()='Hien']");
    public static By SignOutLink= By.xpath("(//a[normalize-space()='Sign Out'])[3]");
    public static By menuTagMyAccount= By.xpath("(//div[@id='secondary'])[2]");
    public static By overviewTag= By.xpath("(//*[@id=\"secondary\"]/nav/div/div/div/a[1])[2]");

}
