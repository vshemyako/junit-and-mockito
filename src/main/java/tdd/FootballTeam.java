package tdd;

/**
 * Represent a single football team.
 */
public class FootballTeam {

    private int gamesWon;

    /**
     * Football team constructor
     *
     * @param gamesWon - number of games a team has won
     */
    public FootballTeam(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * @return number of games a football team has won
     */
    public int getGamesWon() {
        return this.gamesWon;
    }
}
