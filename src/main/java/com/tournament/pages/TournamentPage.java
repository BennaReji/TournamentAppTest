package com.tournament.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TournamentPage extends BasePage {

    // ========== LOCATORS ==========

    // Header
    private static final String TITLE = "//h1[contains(text(), 'Tournament Score Sheet')]";

    // Team Setup Section
    private static final String TEAM_INPUT = "//label[text()='Team %d']/following-sibling::input";
    private static final String ALL_TEAM_INPUTS = "//input[@type='text']";
    private static final String TEAM_BUTTON = "//button[text()='%d Teams']";
    private static final String RESET_BUTTON = "//button[text()='Reset All']";

    // Round Robin Section
    private static final String SCORE_INPUT = "(//input[@placeholder='Score'])[%d]";
    private static final String ALL_SCORE_INPUTS = "//input[@placeholder='Score']";
    private static final String WINNER_CARD = "//div[contains(@class, 'winner-%d')]";
    private static final String MATCH_CARD = "//div[@class='match-card']";
    private static final String TEAM_NAME_IN_MATCH = "//div[@class='team-name'][contains(text(), '%s')]";

    // Standings Section
    private static final String STANDINGS_HEADER = "//h2[text()='Standings']";
    private static final String STANDINGS_TABLE = "//h2[text()='Standings']/following-sibling::table";
    private static final String TABLE_HEADER = "//th[text()='%s']";
    private static final String TABLE_ROWS = "//table//tbody/tr";
    private static final String SPECIFIC_CELL = "//table//tbody/tr[%d]/td[%d]";

    // Playoffs Section
    private static final String PLAYOFF_HEADER = "//h2[text()='Playoffs']";
    private static final String SEMIFINAL = "//h3[text()='Semifinal %d']";
    private static final String CHAMPIONSHIP = "//h3[contains(text(), 'Championship')]";
    private static final String CHAMPION_BANNER = "//div[contains(@class, 'champion-banner')]";
    private static final String PLAYOFF_SEED = "//div[@class='playoff-team'][contains(text(), '#%d:')]";

    // ========== CONSTRUCTOR ==========

    public TournamentPage(WebDriver driver) {
        super(driver);
    }

    // ========== NAVIGATION ==========

    public void navigateTo(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        waitForElement(By.xpath(TITLE));
    }

    // ========== HEADER SECTION ==========

    public boolean isTitleDisplayed() {
        return driver.findElement(By.xpath(TITLE)).isDisplayed();
    }

    public String getTitleText() {
        return driver.findElement(By.xpath(TITLE)).getText();
    }

    // ========== TEAM SETUP SECTION ==========

    // Team Count Toggle
    public void selectTeamCount(int count) {
        WebElement button = driver.findElement(By.xpath(String.format(TEAM_BUTTON, count)));
        button.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(ALL_TEAM_INPUTS), count));
    }

    public int getTeamInputCount() {
        return driver.findElements(By.xpath(ALL_TEAM_INPUTS)).size();
    }

    public boolean isTeamButtonActive(int count) {
        WebElement button = driver.findElement(By.xpath(String.format(TEAM_BUTTON, count)));
        return button.getAttribute("class").contains("active");
    }

    // Team Names
    public void setTeamName(int teamNumber, String name) {
        WebElement input = driver.findElement(By.xpath(String.format(TEAM_INPUT, teamNumber)));
        input.clear();
        input.sendKeys(name);
    }

    public String getTeamName(int teamNumber) {
        return driver.findElement(By.xpath(String.format(TEAM_INPUT, teamNumber)))
                .getAttribute("value");
    }

    public boolean isTeamNameInMatch(String teamName) {
        try {
            return driver.findElement(By.xpath(String.format(TEAM_NAME_IN_MATCH, teamName)))
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Reset
    public void clickReset() {
        driver.findElement(By.xpath(RESET_BUTTON)).click();
        handleAlert();
    }

    private void handleAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            // No alert present
        }
    }

    // ========== ROUND ROBIN SECTION ==========

    public void enterScore(int inputNumber, String score) {
        WebElement input = driver.findElement(By.xpath(String.format(SCORE_INPUT, inputNumber)));
        input.clear();
        input.sendKeys(score);
    }

    public String getScore(int inputNumber) {
        return driver.findElement(By.xpath(String.format(SCORE_INPUT, inputNumber)))
                .getAttribute("value");
    }

    public int getTotalScoreInputs() {
        return driver.findElements(By.xpath(ALL_SCORE_INPUTS)).size();
    }

    public boolean isWinnerHighlighted(int winnerNumber) {
        try {
            return driver.findElement(By.xpath(String.format(WINNER_CARD, winnerNumber)))
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public int getMatchCardCount() {
        return driver.findElements(By.xpath(MATCH_CARD)).size();
    }

    // ========== STANDINGS SECTION ==========

    public boolean isStandingsHeaderDisplayed() {
        return driver.findElement(By.xpath(STANDINGS_HEADER)).isDisplayed();
    }

    public boolean isStandingsTableDisplayed() {
        return driver.findElement(By.xpath(STANDINGS_TABLE)).isDisplayed();
    }

    public boolean isStandingsHeaderPresent(String headerName) {
        try {
            return driver.findElement(By.xpath(String.format(TABLE_HEADER, headerName)))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getStandingsRowCount() {
        return driver.findElements(By.xpath(TABLE_ROWS)).size();
    }

    public String getStandingsCellValue(int row, int column) {
        return driver.findElement(By.xpath(String.format(SPECIFIC_CELL, row, column))).getText();
    }

    public String getFirstPlaceTeam() {
        return getStandingsCellValue(1, 2); // Row 1, Column 2 (Team name)
    }

    // ========== PLAYOFFS SECTION ==========

    public void scrollToPlayoffs() {
        WebElement playoffHeader = driver.findElement(By.xpath(PLAYOFF_HEADER));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", playoffHeader);
    }

    public boolean isPlayoffHeaderDisplayed() {
        return driver.findElement(By.xpath(PLAYOFF_HEADER)).isDisplayed();
    }

    public boolean isSemifinalDisplayed(int semifinalNumber) {
        try {
            return driver.findElement(By.xpath(String.format(SEMIFINAL, semifinalNumber)))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isChampionshipDisplayed() {
        try {
            return driver.findElement(By.xpath(CHAMPIONSHIP)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlayoffSeedDisplayed(int seedNumber) {
        try {
            return driver.findElement(By.xpath(String.format(PLAYOFF_SEED, seedNumber)))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isChampionBannerDisplayed() {
        try {
            return driver.findElement(By.xpath(CHAMPION_BANNER)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getChampionText() {
        return driver.findElement(By.xpath(CHAMPION_BANNER)).getText();
    }

    // ========== HELPER METHODS ==========

    public void enterMultipleScores(int[][] scores) {
        for (int i = 0; i < scores.length; i++) {
            enterScore(i * 2 + 1, String.valueOf(scores[i][0]));
            enterScore(i * 2 + 2, String.valueOf(scores[i][1]));
        }
    }

    public void setAllTeamNames(String[] teamNames) {
        for (int i = 0; i < teamNames.length; i++) {
            setTeamName(i + 1, teamNames[i]);
        }
    }
}