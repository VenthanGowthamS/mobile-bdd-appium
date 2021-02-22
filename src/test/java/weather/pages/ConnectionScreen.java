package weather.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

/** First screen: shows the connection status and the button that fetches weather data. */
public class ConnectionScreen extends BasePage {

    private static final String ID = "com.dyson.recruitment.test:id/";

    @AndroidFindBy(id = ID + "connection_display_icon")
    private MobileElement connectionIcon;

    @AndroidFindBy(id = ID + "connection_display_primary_button")
    private MobileElement fetchDataButton;

    public ConnectionScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean isConnected() {
        return visible(connectionIcon).isDisplayed();
    }

    public FetchDataScreen fetchData() {
        visible(fetchDataButton).click();
        return new FetchDataScreen(driver);
    }
}
