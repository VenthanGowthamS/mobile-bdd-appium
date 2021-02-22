package weather.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

/** Shown while and after the weather data downloads. */
public class FetchDataScreen extends BasePage {

    private static final String ID = "com.dyson.recruitment.test:id/";

    @AndroidFindBy(id = ID + "fetch_data_title")
    private MobileElement title;

    @AndroidFindBy(id = ID + "fetch_data_primary_button")
    private MobileElement viewResultsButton;

    public FetchDataScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    /** Status text with surrounding whitespace removed, e.g. "Data Collected". */
    public String status() {
        return visible(title).getText().trim();
    }

    public WeatherScreen viewResults() {
        visible(viewResultsButton).click();
        return new WeatherScreen(driver);
    }
}
