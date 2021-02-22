package weather.support;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates and holds the Appium session. Every setting can be overridden with -Dname=value,
 * so the same suite runs on any emulator or device without code changes.
 */
public final class DriverFactory {

    private static AndroidDriver<MobileElement> driver;

    private DriverFactory() {
    }

    public static AndroidDriver<MobileElement> start() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", setting("device.name", "sdk_gphone_x86_arm"));
        caps.setCapability("udid", setting("device.udid", "emulator-5554"));
        caps.setCapability("platformVersion", setting("platform.version", "11"));
        caps.setCapability("appPackage", setting("app.package", "com.dyson.recruitment.test"));
        caps.setCapability("appActivity",
                setting("app.activity", "com.dyson.recruitment.test.ui.splash.SplashScreenActivity"));

        URL server = new URL(setting("appium.url", "http://127.0.0.1:4723/wd/hub"));
        driver = new AndroidDriver<>(server, caps);
        return driver;
    }

    public static AndroidDriver<MobileElement> get() {
        if (driver == null) {
            throw new IllegalStateException("Appium session has not been started");
        }
        return driver;
    }

    /** Ends the session if one was started. Safe to call when start() failed. */
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static String setting(String name, String defaultValue) {
        return System.getProperty(name, defaultValue);
    }
}
