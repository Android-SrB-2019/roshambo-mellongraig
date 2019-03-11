/**
 * Author: Graig Mellon
 * Date: 3/10/2019
 */

package roshambo.graigmellon.nbcc.work.roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //initialize global variables
    private TextView determineResult;
    private ImageView humanPlayerSelection;
    private ImageView computerPlayerSelection;

    //call this to start a new rochambo game
    Rochambo startNewGame = new Rochambo();

    /**
     * onCreate method called to initialize the activity and also assign the variables
     * @param savedInstanceState current status of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        determineResult = findViewById(R.id.game_outcome);
        humanPlayerSelection = findViewById(R.id.human_player_select);
        computerPlayerSelection = findViewById(R.id.computer_player_select);
    }

    /**
     * By a click of the button, the player's selection will be displayed
     * The game selection method will also be called
     * Animation will also be called
     * @param view Player's selection
     */
    public void moveSelection(View view) {
        Integer playerSelect = (Integer) view.getTag();
        Integer outcome = startNewGame.winLoseOrDraw();
        determineResult.setText(outcome);
        gameSelection();
        animateSelection();
        switch(playerSelect){
            case Rochambo.ROCK:
                startNewGame.playerMakesMove(0);
                humanPlayerSelection.setImageResource(R.drawable.rock);
                break;
            case Rochambo.PAPER:
                startNewGame.playerMakesMove(1);
                humanPlayerSelection.setImageResource(R.drawable.paper);
                break;
            case Rochambo.SCISSOR:
                startNewGame.playerMakesMove(2);
                humanPlayerSelection.setImageResource(R.drawable.scissors);
                break;
            case Rochambo.NONE:
                //do nothing here
                break;
        }
    }

    /**
     * The computer player will make a selection within the boundaries of the randomizer
     * by choosing rock, paper, or scissors
     */
    public void gameSelection(){
        if(startNewGame.getGameMove() == 0){
            computerPlayerSelection.setImageResource(R.drawable.rock);
        } else if (startNewGame.getGameMove() == 1){
            computerPlayerSelection.setImageResource(R.drawable.paper);
        } else if (startNewGame.getGameMove() == 2){
            computerPlayerSelection.setImageResource(R.drawable.scissors);
        }
    }

    /**
     * This will animate the player and computer player's images together
     */
    public void animateSelection(){
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(humanPlayerSelection,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(computerPlayerSelection,
                "rotationY", 0f, -360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }
}
