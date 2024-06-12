package keywords;

import com.aventstack.extentreports.Status;
import drivers.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.AllureManager;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int TIMEOUT = 10; //time chờ của WebDriverWait
    private static double STEP_TIME = 0.5; // time chờ đợi của hàm sleep
    private static int PAGE_LOAD_TIMEOUT = 60;//time chờ đợi trang load xong

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static boolean checkElementExist(By by) {
        List<WebElement> listElement = getWebElements(by); //DriverManager.getDriver().findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " exist.");
            return true;
        } else {
            System.out.println("Element " + by + " Not exist.");
            return false;
        }
    }

    public static boolean checkElementDisplayed(By by) {
        //waitForElementVisible(by);
        WebUI.sleep(3);
        boolean check = getWebElement(by).isDisplayed();
        return check;
    }

    @Step("Open URL: {0}")
    public static void opeURL(String url) {
        DriverManager.getDriver().get(url);
        System.out.println("Open URL: " + url);
    }

    @Step("Click Element: {0}")
    public static void clickElement(By by) {
        //waitForElementClickable(by);
        WebUI.sleep(5);
        getWebElement(by).click();
        System.out.println("Click Element: " + by);
    }

    @Step("Input text: {1} on element {0}")
    public static void setText(By by, String value) {
        //waitForElementVisible(by);
        WebUI.sleep(1);
        getWebElement(by).sendKeys(value);
        System.out.println("Input text: " + "'" + value + "'" + " on input: " + by);
    }


    @Step("GET TEXT OF ELEMENT: {0} ")
    public static String getElementText(By by) {
        //waitForElementVisible(by);
        WebUI.sleep(2);
        WebUI.sleep(STEP_TIME);
        String text = DriverManager.getDriver().findElement(by).getText();
        System.out.println("GET TEXT OF ELEMENT: '" + by + "'" + " ===> " + text);
        AllureManager.saveTextLog("\uD83C\uDF49===> " + text);
        return text;
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(1800));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                Assert.fail("❌FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(1100));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());

        }
    }

    public static void captureScreenImage(String imageName) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        //Get size screen browser
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("Kích thước khung hình: " + screenSize);
        //Khởi tạo kích thước khung hình với kích cỡ trên
        Rectangle screenRectangle = new Rectangle(screenSize);
        //Tạo hình chụp với độ lớn khung đã tạo trên
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //Lưu hình vào dạng file với dạng png
        File file = new File("src/test/resources/screenshots/" + imageName + ".png");
        try {
            ImageIO.write(image, "png", file);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static WebElement highLightElement(By by) {
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='2px solid blue'", getWebElement(by));
            sleep(1);
        }
        return getWebElement(by);
    }

}
