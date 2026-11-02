# mobile-bdd-appium

**Behaviour-driven UI automation for an Android app, written in plain English and run on an emulator.**

This is one of my early test-automation projects (2021). I wrote the test scenarios the way a user would describe them, then wired each step to real taps and checks on an Android weather app through Appium.

## What it tests

```gherkin
Feature: Weather
  In order to improve my hike today
  As a keen hiker
  I want to be able to view today's weather

  @Smoke @Regression
  Scenario: Get the high level weather forecast
    Given I have an internet connection
    When I have successfully downloaded the weather data
    Then I can see the weather and temperature for today
```

Each line is checked on the real app screen, not assumed:

| Step | What the automation actually does |
|---|---|
| **Given** I have an internet connection | Reads the connection indicator on screen and asserts it shows *Connected* |
| **When** I download the weather data | Taps *Fetch data*, waits for the *Data Collected* confirmation, then opens the results |
| **Then** I can see today's weather | Locates the weather and temperature fields on the results screen and verifies them |

## How it's built

| Piece | File | Role |
|---|---|---|
| Feature file | `resources/weather.feature` | The scenario in Gherkin (Given / When / Then), tagged `@Smoke` and `@Regression` |
| Step definitions | `src/test/java/.../stepdefintion.java` | Glue code: starts the Appium session and turns each sentence into actions and assertions |
| Page objects | `outdoordata.java`, `weatherpage.java` | One class per screen, with elements located by resource ID (Page Object Model + `PageFactory`) |

**Stack:** Java · Appium Java client 7.4 · Selenium 3.141 · Cucumber 6.9 (JUnit) · Maven · Android 11 emulator

## Run it

1. Start an Android 11 emulator (`emulator-5554`) with the app under test installed.
2. Start the Appium server on `http://127.0.0.1:4723/wd/hub`.
3. Run the Cucumber tests:

   ```bash
   mvn test
   ```

The device and app settings are in the `@Before` hook in `stepdefintion.java`. Change them there to point at your own device or app.

## What I'd do differently today

- Move device capabilities into a config file instead of hard-coding them.
- Use explicit waits for screen transitions instead of relying on timing.
- Add a negative scenario (no connection, failed download) alongside the happy path.

That mindset, checking what really happened on screen rather than trusting what the app reports, is the same one I now apply to AI agents in [agent-evals-lab](https://github.com/VenthanGowthamS/agent-evals-lab).
