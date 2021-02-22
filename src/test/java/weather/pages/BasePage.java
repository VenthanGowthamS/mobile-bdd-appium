package weather.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Shared plumbing for screens: element wiring and explicit waits instead of fixed sleeps. */
abstract class BasePage {

    private static final long TIMEOUT_SECONDS = 15;

    protected final AndroidDriver<MobileElement> driver;
    private final WebDriverWait wait;

    BasePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected MobileElement visible(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }
}
