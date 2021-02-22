# mobile-bdd-appium

**Behaviour-driven UI tests for an Android weather app: plain-English scenarios, run on an emulator through Appium.**

The scenario describes what a hiker expects from the app. Each step taps through the real app screens and checks what is actually displayed, not what the app claims happened.

## What it tests

```gherkin
Feature: Weather
  In order to plan my hike today
  As a keen hiker
  I want to be able to view today's weather

  @smoke @regression
  Scenario: Get the high level weather forecast
    Given I have an internet connection
    When I have successfully downloaded the weather data
    Then I can see the weather and temperature for today
```

| Step | What the automation does |
|---|---|
| **Given** I have an internet connection | Waits for the connection indicator on the first screen and checks it is shown |
| **When** I download the weather data | Taps *Fetch data*, checks the status reads exactly *Data Collected*, then opens the results |
| **Then** I can see today's weather | Checks a weather summary is shown and the temperature is a real reading such as `10.39C` or `-2 °C` |

The last check validates the format, not one day's forecast, so the test doesn't break when the weather changes.

## How it's built

```
src/test/
├── resources/features/weather.feature   ← scenario in plain English
└── java/weather/
    ├── runner/RunCucumberTest.java      ← JUnit entry point for `mvn test`, HTML report
    ├── steps/WeatherSteps.java          ← each sentence → taps + assertions
    ├── steps/Hooks.java                 ← start / end the Appium session
    ├── pages/ConnectionScreen.java      ← screen objects, one per app screen
    ├── pages/FetchDataScreen.java
    ├── pages/WeatherScreen.java
    ├── pages/BasePage.java              ← element wiring + explicit waits
    └── support/DriverFactory.java       ← device and app settings, overridable
```

- **Screen objects:** element IDs live in one class per screen, so steps read like the scenario.
- **Explicit waits:** each check waits for the element it needs instead of relying on timing.
- **Configurable:** device, Android version, app and Appium URL can be changed from the command line without code edits.

**Stack:** Java 8+ · Appium Java client 7.4 (UiAutomator2) · Cucumber 6.9 · JUnit 4 · Maven

## Run it

1. Start an Android emulator or plug in a device, with the app under test installed.
2. Start the Appium server (default `http://127.0.0.1:4723/wd/hub`).
3. Run:

   ```bash
   mvn test
   ```

To target a different device or server:

```bash
mvn test -Ddevice.udid=emulator-5556 -Dplatform.version=12 -Dappium.url=http://127.0.0.1:4723/wd/hub
```

The report is written to `target/cucumber-report.html`.
