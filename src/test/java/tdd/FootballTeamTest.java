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
     * Prepares array of params for future tests
     */
    public static Object[] getIntParams() {
        return new Object[]{
                -10, 0, 10
        };
    }

    /**
     * Constructor should initialize object fields: in particular {@link FootballTeam#gamesWon} field.
     * Test verifies this behavior.
     */
    @Test
    @Parameters(method = "getIntParams")
    public void constructorShouldSetGamesWon(int gamesWon) {
        FootballTeam footballTeam = new FootballTeam(gamesWon);
        assertEquals("Constructor doesn't set gamesWon number", gamesWon, footballTeam.getGamesWon());
    }
}
