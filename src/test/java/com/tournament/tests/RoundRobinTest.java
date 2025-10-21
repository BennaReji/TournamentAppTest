package com.tournament.tests;

import com.tournament.utils.TestBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoundRobinTest extends TestBase {

    @Test
    public void testCorrectNumberOfMatches() {
        // 4 teams = 6 matches (4C2)
        assertEquals("Should have 6 matches for 4 teams", 6, tournamentPage.getMatchCardCount());
    }

    @Test
    public void testEnterSingleScore() {
        tournamentPage.enterScore(1, "30");
        assertEquals("First score should be 30", "30", tournamentPage.getScore(1));
    }

    @Test
    public void testEnterBothScores() {
        tournamentPage.enterScore(1, "40");
        tournamentPage.enterScore(2, "25");

        assertEquals("First score should be 40", "40", tournamentPage.getScore(1));
        assertEquals("Second score should be 25", "25", tournamentPage.getScore(2));
    }

    @Test
    public void testWinnerHighlighting() {
        tournamentPage.enterScore(1, "50");
        tournamentPage.enterScore(2, "30");

        assertTrue("Winner 1 should be highlighted", tournamentPage.isWinnerHighlighted(1));
    }

    @Test
    public void testLoserNotHighlighted() {
        tournamentPage.enterScore(1, "20");
        tournamentPage.enterScore(2, "35");

        assertTrue("Winner 2 should be highlighted", tournamentPage.isWinnerHighlighted(2));
        assertFalse("Winner 1 should not be highlighted", tournamentPage.isWinnerHighlighted(1));
    }

    @Test
    public void testMultipleMatchScores() {
        int[][] scores = {{30, 25}, {40, 35}, {28, 22}};
        tournamentPage.enterMultipleScores(scores);

        assertEquals("Match 1 score 1 should be 30", "30", tournamentPage.getScore(1));
        assertEquals("Match 1 score 2 should be 25", "25", tournamentPage.getScore(2));
    }

    @Test
    public void testResetClearsScores() {
        tournamentPage.enterScore(1, "50");
        tournamentPage.enterScore(2, "40");
        tournamentPage.clickReset();

        assertEquals("First score should be cleared", "", tournamentPage.getScore(1));
        assertEquals("Second score should be cleared", "", tournamentPage.getScore(2));
    }
}