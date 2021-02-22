package weather.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

/** Results screen with today's weather summary and temperature. */
public class WeatherScreen extends BasePage {

    private static final String ID = "com.dyson.recruitment.test:id/";

    @AndroidFindBy(id = ID + "weather_display_weather_value")
    private MobileElement weather;

    @AndroidFindBy(id = ID + "weather_display_temperature_value")
    private MobileElement temperature;

    public WeatherScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    public String weather() {
        return visible(weather).getText().trim();
    }

    public String temperature() {
        return visible(temperature).getText().trim();
    }
}
