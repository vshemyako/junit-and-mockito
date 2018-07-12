package tdd;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Tests functionality of {@link FootballTeam} class
 */
@RunWith(JUnitParamsRunner.class)
public class FootballTeamTest {

    /**
     * Prepares array of params with valid games number
     */
    private static Object[] getValidGamesWonNumber() {
        return new Object[]{
                0, 1, 10, 20
        };
    }

    /**
     * Prepares array of params with invalid games number
     */
    private static Object[] getInvalidGamesWonNumber() {
        return new Object[]{
                -10, -5, -1
        };
    }

    /**
     * Constructor should initialize object fields: in particular {@link FootballTeam#gamesWon} field.
     * Test verifies this behavior.
     */
    @Test
    @Parameters(method = "getValidGamesWonNumber")
    public void constructorShouldSetGamesWon(int gamesWon) {
        FootballTeam footballTeam = new FootballTeam(gamesWon);
        assertEquals("Constructor doesn't set gamesWon number", gamesWon, footballTeam.getGamesWon());
    }

    /**
     * Constructor should throw {@link IllegalArgumentException} if negative number of games won is passed.
     */
    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidGamesWonNumber")
    public void constructorShouldThrowExceptionOnNegativeGamesWon(int gamesWon) {
        new FootballTeam(gamesWon);
    }
}
