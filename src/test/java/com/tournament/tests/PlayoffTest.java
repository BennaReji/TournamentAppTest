package com.tournament.tests;

import com.tournament.utils.TestBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayoffTest extends TestBase {

    @Test
    public void testPlayoffHeaderDisplayed() {
        tournamentPage.scrollToPlayoffs();
        assertTrue("Playoff header should be displayed",
                tournamentPage.isPlayoffHeaderDisplayed());
    }

    @Test
    public void testBothSemifinalsDisplayed() {
        tournamentPage.scrollToPlayoffs();
        assertTrue("Semifinal 1 should be displayed", tournamentPage.isSemifinalDisplayed(1));
        assertTrue("Semifinal 2 should be displayed", tournamentPage.isSemifinalDisplayed(2));
    }

    @Test
    public void testChampionshipDisplayed() {
        tournamentPage.scrollToPlayoffs();
        assertTrue("Championship should be displayed", tournamentPage.isChampionshipDisplayed());
    }

    @Test
    public void testAllSeedsDisplayed() {
        tournamentPage.scrollToPlayoffs();

        for (int seed = 1; seed <= 4; seed++) {
            assertTrue("Seed #" + seed + " should be displayed",
                    tournamentPage.isPlayoffSeedDisplayed(seed));
        }
    }

    @Test
    public void testPlayoffScoring() {
        tournamentPage.scrollToPlayoffs();

        // Get playoff score inputs (after round robin inputs)
        int roundRobinInputs = 12; // 6 matches * 2 inputs
        tournamentPage.enterScore(roundRobinInputs + 1, "55");
        tournamentPage.enterScore(roundRobinInputs + 2, "50");

        assertEquals("55", tournamentPage.getScore(roundRobinInputs + 1));
    }
}