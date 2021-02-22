package weather.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import weather.pages.ConnectionScreen;
import weather.pages.FetchDataScreen;
import weather.pages.WeatherScreen;
import weather.support.DriverFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WeatherSteps {

    /** A number with optional decimals followed by a unit, e.g. "10.39C", "-2 °C" or "50.5F". */
    private static final String TEMPERATURE_FORMAT = "-?\\d+(\\.\\d+)?\\s?°?[CF]";

    private ConnectionScreen connectionScreen;
    private WeatherScreen weatherScreen;

    @Given("I have an internet connection")
    public void iHaveAnInternetConnection() {
        connectionScreen = new ConnectionScreen(DriverFactory.get());
        assertTrue("Connection indicator should be shown", connectionScreen.isConnected());
    }

    @When("I have successfully downloaded the weather data")
    public void iHaveDownloadedTheWeatherData() {
        FetchDataScreen fetchDataScreen = connectionScreen.fetchData();
        assertEquals("Data Collected", fetchDataScreen.status());
        weatherScreen = fetchDataScreen.viewResults();
    }

    @Then("I can see the weather and temperature for today")
    public void iCanSeeTodaysWeather() {
        // Weather changes every day, so check that real values are shown in the right format
        // instead of comparing against one day's forecast.
        String weather = weatherScreen.weather();
        String temperature = weatherScreen.temperature();

        assertFalse("Weather summary should not be empty", weather.isEmpty());
        assertTrue("Unexpected temperature format: " + temperature, temperature.matches(TEMPERATURE_FORMAT));
    }
}
