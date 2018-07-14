package tdd.football;

/**
 * Represent a single football team.
 */
public class FootballTeam implements Comparable<FootballTeam> {

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

    /**
     * Natural ordering of football teams implies that a team with
     * lower number of games won will return negative number, with
     * greater number of games won will return positive number, zero
     * in case number of games is equal.
     */
    @Override
    public int compareTo(FootballTeam anotherTeam) {
        return this.getGamesWon() - anotherTeam.getGamesWon();
    }
}
