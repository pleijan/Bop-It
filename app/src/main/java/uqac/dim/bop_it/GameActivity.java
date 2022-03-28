package uqac.dim.bop_it;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView CountDownText;
    private long timeLeftInMilliseconds = 300000; // Time in milliseconds
    private boolean countdownIsActive;
    private int nombreDepreuveReussi  = 0;


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
        startGame();
    }

    private void startGame() {
        CountDownText = findViewById(R.id.countdowm_timer);
        startTimer();

       // while(timeLeftInMilliseconds > 0) {
            //askAndLaunchRandomActions();
       // }


        //lancement timer
            //if epreuve fini avant le temps impartie
            //autre epreuve
            //
            //sinon fin du jeu
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
        final int random = new Random().nextInt(1) + 1;
       //selon le random généré, choisi une fonction
        switch(random) {
            case 1:
                //bop-it
                break;
            default: // bop-it
                break;
                // code block
        }
    }

    public void bopItAction(){

    }

}
