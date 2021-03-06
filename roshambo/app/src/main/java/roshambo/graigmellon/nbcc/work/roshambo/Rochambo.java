/**
 * Author: Graig Mellon
 * Date: 3/10/2019
 */

package roshambo.graigmellon.nbcc.work.roshambo;

import java.util.Random;

public class Rochambo {

    //initialize the calls
    public static final int ROCK = 0;
    public static final int PAPER = 1;
    public static final int SCISSOR = 2;
    public static final int NONE = 3;

    //initialize the results for the game outcome
    public static final int WIN = R.string.win;
    public static final int LOSE = R.string.lose;
    public static final int DRAW = R.string.draw;

    //initialize the moves for human and computer players as well to randomize
    private int gameMove;
    private int playerMove;
    private Random rand;

    /**
     * Creates a new Rochambo to start the game
     */
    public Rochambo() {
        rand = new Random();
        gameMove = rand.nextInt(2);
        playerMove = NONE;
    }

    /**
     * Player chooses their next move, the game immediately follows and
     * makes it's next move. Use winLoseOrDraw() to see the outcome
     *
     * @param playerMove  ROCK, PAPER, or SCISSOR are valid arguments
     */
     public void playerMakesMove(int playerMove) {

        if ( Integer.compareUnsigned(playerMove , NONE) > 0)
            throw new IllegalArgumentException("not a valid player move");

        this.playerMove = playerMove;
        gameMakesMove();
    }

    /**
     * The game makes a random choice of ROCK, PAPER, or SCISSOR
     */
    private void gameMakesMove() {
        gameMove = rand.nextInt(3);
    }

    /**
     * Gets the game move based on the randomizer input
     * @return ROCK, PAPER, or SCISSOR
     */
    public int getGameMove() {
        return gameMove;
    }

    /**
     * Gets the player's move on the click of a button
     * @return ROCK, PAPER, or SCISSOR
     */
    public int getPlayerMove() {
        return playerMove;
    }

    /**
     * The winLoseOrDraw() method will determine the result based on the player
     * selection and the computer player's selection and will return LOSE if the
     * player loses, WIN if the player wins, or DRAW if both the human player
     * and the computer player return the same result
     * @return result based on the player and computer player's selections
     */
    public int winLoseOrDraw() {

        if (playerMove == ROCK) {
            if (gameMove == ROCK) {
                return DRAW;
            } else if (gameMove == PAPER) {
                return LOSE;
            } else {
                return WIN;
            }
        } else if (playerMove == PAPER ) {
            if (gameMove == PAPER) {
                return DRAW;
            } else if (gameMove == SCISSOR) {
                return LOSE;
            } else {
                return WIN;
            }
        } else if (playerMove == SCISSOR ) {
            if (gameMove == SCISSOR) {
                return DRAW;
            } else if (gameMove == ROCK) {
                return LOSE;
            } else {
                return WIN;
            }
        }

        return DRAW; //shouldn't get here
    }


}
