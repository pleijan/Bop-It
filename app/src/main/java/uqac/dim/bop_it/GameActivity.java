package uqac.dim.bop_it;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView CountDownText;
    private long timeLeftInMilliseconds = 300000; // Time in milliseconds
    private boolean timerIsRunning ;
    private TextView actionRequiredText;
    private int actionSucceed  = 0;

    private enum ActionRequired {
        BOPIT,
        NONE
    }


    private ActionRequired actionRequired = ActionRequired.NONE;

    String pseudo,timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page_de_jeu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pseudo = extras.getString("pseudo");
            Log.i("DIM", pseudo);
        }

        //Ajout de l'action sur le bouton pour le jeu

        final Button button = (Button) findViewById(R.id.BopITButton);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          if (actionRequired == ActionRequired.BOPIT ){
                                              actionSucceed++;
                                              askAndLaunchRandomActions();
                                          }
                                          else {
                                              gameOver(v);
                                          }
                                      }
                                  });
        startGame();
    }

    private void startGame() {
        CountDownText = findViewById(R.id.countdowm_timer);
        actionRequiredText = findViewById(R.id.AskedAction);
        timerIsRunning = true;
        startTimer();

       askAndLaunchRandomActions(); // les actions utilisateur déterminerons si on relance une actiondemandé
    }

    public void gameOver(View view) {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("pseudo",pseudo);
        intent.putExtra("timer",timer);
        startActivity(intent);
    }

    public void pauseMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startTimer() {
        new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long i) {
                timeLeftInMilliseconds = i;
                updateTimer();
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void updateTimer(){
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000/1000;

        String timeLeftInText;

        timeLeftInText = "" + minutes;
        timeLeftInText = timeLeftInText + ":";

        if (seconds < 10 ) timeLeftInText = timeLeftInText + "0";
        timeLeftInText = timeLeftInText + seconds;

        CountDownText.setText(timeLeftInText);
    }

    public void askAndLaunchRandomActions(){
         int random = new Random().nextInt(3) + 1; // from 1 to 1 (random 1 = 0)
       //selon le random généré, choisi une fonction
        Log.d("DIM", "RANDOM: "+Integer.toString(random));
        switch(random) {
            case 1:
                bopItAction();//demande de pousser le bouton
                break;
            case 2:
                SwipeAction();
                break;
            case 3:
                bopItActionMaisPourTest();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default: // bop-it
                break;
                // code block


        }
    }

    public void bopItAction(){
        actionRequiredText.setText("Bop-IT!");
        actionRequired = ActionRequired.BOPIT; //TODO mis a none a des din de test, doit etre remis a BOPIT apres, note au cas
    }
    public void bopItActionMaisPourTest(){
        actionRequiredText.setText("Fait rien");
        actionRequired = ActionRequired.NONE; //TODO mis a none a des din de test, doit etre remis a BOPIT apres, note au cas
    }
    public void SwipeAction(){
        SwipeTest FSwipe = new SwipeTest();
        getSupportFragmentManager().beginTransaction()
                .commit();
    }
}
