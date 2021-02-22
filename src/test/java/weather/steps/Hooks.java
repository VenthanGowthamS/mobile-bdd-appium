package weather.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import weather.support.DriverFactory;

import java.net.MalformedURLException;

public class Hooks {

    @Before
    public void startApp() throws MalformedURLException {
        DriverFactory.start();
    }

    @After
    public void closeApp() {
        DriverFactory.quit();
    }
}
