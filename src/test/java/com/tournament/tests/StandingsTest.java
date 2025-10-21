package com.tournament.tests;

import com.tournament.utils.TestBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class StandingsTest extends TestBase {

    @Test
    public void testStandingsHeaderDisplayed() {
        assertTrue("Standings header should be displayed",
                tournamentPage.isStandingsHeaderDisplayed());
    }

    @Test
    public void testStandingsTableDisplayed() {
        assertTrue("Standings table should be displayed",
                tournamentPage.isStandingsTableDisplayed());
    }

    @Test
    public void testStandingsHasAllHeaders() {
        String[] headers = {"Rank", "Team", "Wins", "Losses",
                "Points For", "Points Against", "Differential"};

        for (String header : headers) {
            assertTrue(header + " should be present",
                    tournamentPage.isStandingsHeaderPresent(header));
        }
    }

    @Test
    public void testStandingsHasFourTeams() {
        assertEquals("Standings should have 4 rows", 4,
                tournamentPage.getStandingsRowCount());
    }

    @Test
    public void testStandingsUpdateAfterScore() {
        tournamentPage.enterScore(1, "35");
        tournamentPage.enterScore(2, "25");

        // First place should show rank 1
        String firstRank = tournamentPage.getStandingsCellValue(1, 1);
        assertEquals("First rank should be 1", "1", firstRank);
    }

    @Test
    public void testWinningTeamInFirstPlace() {
        tournamentPage.setTeamName(1, "Warriors");
        tournamentPage.enterScore(1, "100");
        tournamentPage.enterScore(2, "50");

        String firstPlaceTeam = tournamentPage.getFirstPlaceTeam();
        assertEquals("Warriors should be in first place", "Warriors", firstPlaceTeam);
    }
}