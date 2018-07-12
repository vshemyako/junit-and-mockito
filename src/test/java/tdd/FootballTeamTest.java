package tdd;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    /**
     * Verifies that {@link FootballTeam} class implements {@link Comparable} interface.
     *
     * @param gamesWon - any valid games number
     */
    @Test
    @Parameters(method = "getValidGamesWonNumber")
    public void shouldBePossibleToCompareTeams(int gamesWon) {
        FootballTeam footballTeam = new FootballTeam(gamesWon);
        assertTrue("FootballTeam class should implement Comparable", footballTeam instanceof Comparable);
    }

    /**
     * Verifies that football team with lower number of games won
     * is weaker.
     */
    @Test
    @Parameters(method = "getValidGamesWonNumber")
    public void teamWithLowerNumberOfGamesWonShouldBeWeaker(int gamesWon) {
        FootballTeam strongerTeam = new FootballTeam(gamesWon + 1);
        FootballTeam weakerTeam = new FootballTeam(gamesWon);
        Assert.assertTrue(weakerTeam.compareTo(strongerTeam) < 0);
    }

    /**
     * Verifies that football team with greater number of games won
     * is stronger.
     */
    @Test
    @Parameters(method = "getValidGamesWonNumber")
    public void teamWithGreaterNumberOfGamesWonShouldBeStronger(int gamesWon) {
        FootballTeam strongerTeam = new FootballTeam(gamesWon + 1);
        FootballTeam weakerTeam = new FootballTeam(gamesWon);
        Assert.assertTrue(strongerTeam.compareTo(weakerTeam) > 0);
    }

    /**
     * Verifies that football teams with equal number of games won
     * are equal.
     */
    @Test
    @Parameters(method = "getValidGamesWonNumber")
    public void teamsWithEqualNumberOfGamesWonAreEqual(int gamesWon) {
        FootballTeam strongerTeam = new FootballTeam(gamesWon);
        FootballTeam weakerTeam = new FootballTeam(gamesWon);
        Assert.assertTrue(strongerTeam.compareTo(weakerTeam) == 0);
    }
}
