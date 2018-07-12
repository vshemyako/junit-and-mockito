package tdd;

/**
 * Represent a single football team.
 */
public class FootballTeam {

    /**
     * Number of games a football team has won
     */
    private int gamesWon;

    /**
     * Football team constructor
     *
     * @param gamesWon - number of games a team has won
     */
    public FootballTeam(int gamesWon) {
        if (gamesWon < 0) {
            throw new IllegalArgumentException("Number of games won must be more than zero");
        }

        this.gamesWon = gamesWon;
    }

    /**
     * @return number of games a football team has won
     */
    public int getGamesWon() {
        return this.gamesWon;
    }
}
