package com.tournament.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tournament.pages.TournamentPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
    protected static WebDriver driver;
    protected static TournamentPage tournamentPage;
    protected static final String BASE_URL = "http://localhost:5173";

    protected static ExtentReports extent;
    protected static ExtentTest test;

    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void setUpClass() {
        // Initialize ExtentReports
        extent = ExtentManager.getInstance();

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        tournamentPage = new TournamentPage(driver);
    }

    @Before
    public void setUp() {
        // Create test in report
        test = extent.createTest(testName.getMethodName());
        test.log(Status.INFO, "Starting test: " + testName.getMethodName());

        tournamentPage.navigateTo(BASE_URL);
        test.log(Status.INFO, "Navigated to: " + BASE_URL);
    }

    @After
    public void tearDown() {
        test.log(Status.INFO, "Test completed: " + testName.getMethodName());
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }

        // Flush report
        ExtentManager.flush();
        System.out.println("\n========================================");
        System.out.println("HTML Report generated at: test-output/ExtentReport.html");
        System.out.println("========================================\n");
    }

    // Helper methods for logging
    protected void logPass(String message) {
        test.log(Status.PASS, message);
    }

    protected void logFail(String message) {
        test.log(Status.FAIL, message);
    }

    protected void logInfo(String message) {
        test.log(Status.INFO, message);
    }
}