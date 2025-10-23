package com.tournament.tests;

import com.tournament.utils.TestBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeamManagementTest extends TestBase {

    @Test
    public void testAppLoadsSuccessfully() {
        assertTrue("Title should be displayed", tournamentPage.isTitleDisplayed());
        assertEquals("🏆 Tournament Score Sheet", tournamentPage.getTitleText());
    }

    @Test
    public void testDefaultTeamCount() {
        assertEquals("Should have 4 teams by default", 4, tournamentPage.getTeamInputCount());
        assertTrue("4 Teams button should be active", tournamentPage.isTeamButtonActive(4));
    }

    @Test
    public void testSwitchToFiveTeams() {
        tournamentPage.selectTeamCount(5);
        assertEquals("Should have 5 teams", 5, tournamentPage.getTeamInputCount());
        assertTrue("5 Teams button should be active", tournamentPage.isTeamButtonActive(5));
    }

    @Test
    public void testSwitchToSixTeams() {
        tournamentPage.selectTeamCount(6);
        assertEquals("Should have 6 teams", 6, tournamentPage.getTeamInputCount());
        assertTrue("6 Teams button should be active", tournamentPage.isTeamButtonActive(6));
    }

    @Test
    public void testUpdateSingleTeamName() {
        logInfo("Setting team 1 name to 'Lakers'");
        tournamentPage.setTeamName(1, "Lakers");
        logPass("✓ Team name set to Lakers");

        logInfo("Verifying team name appears in match card");
        assertTrue("Lakers should appear in match", tournamentPage.isTeamNameInMatch("Lakers"));
        logPass("✓ Team name appears in match card");
    }

    @Test
    public void testUpdateMultipleTeamNames() {
        String[] teams = {"Warriors", "Lakers", "Celtics", "Heat"};
        tournamentPage.setAllTeamNames(teams);

        for (int i = 0; i < teams.length; i++) {
            assertEquals(teams[i] + " should be set", teams[i], tournamentPage.getTeamName(i + 1));
        }
    }

    @Test
    public void testResetClearsTeamNames() {
        tournamentPage.setTeamName(1, "Test Team");
        tournamentPage.clickReset();
        assertEquals("Team name should be reset", "Team 1", tournamentPage.getTeamName(1));
    }
}