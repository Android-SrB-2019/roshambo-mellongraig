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

        //initialize all variables
        determineResult = (TextView)findViewById(R.id.game_outcome);
        humanPlayerSelection = (ImageView)findViewById(R.id.human_player_select);
        computerPlayerSelection = (ImageView)findViewById(R.id.computer_player_select);
    }

    /**
     * By a click of the button, the player's selection will be displayed
     * Computer player will also make the selection randomly
     * Animation method will also be called
     * @param view Player selections
     */
    public void moveSelection(View view) {

        switch(view.getId()){
            case R.id.rock_select:
                startNewGame.playerMakesMove(Rochambo.ROCK);
                humanPlayerSelection.setImageResource(R.drawable.rock);
                break;
            case R.id.paper_select:
                startNewGame.playerMakesMove(Rochambo.PAPER);
                humanPlayerSelection.setImageResource(R.drawable.paper);
                break;
            case R.id.scissors_select:
                startNewGame.playerMakesMove(Rochambo.SCISSOR);
                humanPlayerSelection.setImageResource(R.drawable.scissors);
                break;
            default:
                //do nothing for your selection here
                break;
        }

        if(startNewGame.getGameMove() == 0){
            computerPlayerSelection.setImageResource(R.drawable.rock);
        } else if(startNewGame.getGameMove() == 1){
            computerPlayerSelection.setImageResource(R.drawable.paper);
        } else if(startNewGame.getGameMove() == 2){
            computerPlayerSelection.setImageResource(R.drawable.scissors);
        }
        //determine the outcome selected
        Integer outcome = startNewGame.winLoseOrDraw();
        determineResult.setText(outcome);
        animateSelection();
    }

    /**
     * This will animate the player and computer player's images together for half a second
     * Rotation Y will go counter-clockwise
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
