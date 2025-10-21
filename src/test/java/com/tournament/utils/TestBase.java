package com.tournament.utils;

import com.tournament.pages.TournamentPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
    protected static WebDriver driver;
    protected static TournamentPage tournamentPage;
    protected static final String BASE_URL = "http://localhost:5173";

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        tournamentPage = new TournamentPage(driver);
    }

    @Before
    public void setUp() {
        tournamentPage.navigateTo(BASE_URL);
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}