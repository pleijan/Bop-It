package uqac.dim.bop_it;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.view.MotionEvent;

public class GameActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;

    float x1, x2, y1, y2, diffx, diffy;
    //SwipeListener swipelistener;


    private TextView CountDownText;
    private long timeLeftInMilliseconds = 300000; // Time in milliseconds
    private boolean timerIsRunning ;
    public TextView actionRequiredText;
    private int actionSucceed  = 0; // UTILISER POUR LE POINTAGE FINAL


    /**
     * type de lactivite lancee
     *
     */
    private enum ActionRequired {
        BOPITCENTRE,
        BOPITHAUTDROITE,
        BOPITHAUTGAUCHE,
        BOPITBASGAUCHE,
        BOPITBASDROITE,
        SWIPELEFT,
        SWIPEUP,
        SWIPERIGHT,
        SWIPEDOWN,
        NONE
    }

    private ActionRequired actionRequired = ActionRequired.NONE;
    String pseudo;
    int timer;
    /**
     * à la création de l'activite
     * @param savedInstanceState
     */
    @SuppressLint("ClickableViewAccessibility")
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

        /**
         * Si le bouton es cliqué, vérifie si c'était bel et bien l'action requise
         * Si oui, lance une autre activitée au hasard
         * sinon, fin de la partie (
         */
        Button bopItButtonCentral = (Button)findViewById(R.id.bopITButtonCentral); //todo trouver bouton
        Button bopItButtonHautGauche = (Button)findViewById(R.id.bopITButtonHautGauche); //todo trouver bouton
        Button bopItButtonHautDroite = (Button)findViewById(R.id.bopITButtonHautDroite); //todo trouver bouton
        Button bopItButtonBasGauche = (Button)findViewById(R.id.bopITButtonBasGauche); //todo trouver bouton
        Button bopItButtonBasDroite = (Button)findViewById(R.id.BopITButtonBasDroite); //todo trouver bouton

        /**
         * Creation de tous les boutons, puis on les cache pour l'activité
         * Bouton
         */
        bopItButtonCentral.setVisibility(View.GONE);
        bopItButtonHautDroite.setVisibility(View.GONE);
        bopItButtonHautGauche.setVisibility(View.GONE);
        bopItButtonBasGauche.setVisibility(View.GONE);
        bopItButtonBasDroite.setVisibility(View.GONE);

        /**
         * creation interface tactile
         */

        ImageButton relativebutton = (ImageButton) findViewById(R.id.relativebutton);
        relativebutton.setOnTouchListener((new View.OnTouchListener() {
            View v;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = motionEvent.getX();// Do what you want
                        y1 = motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        x2 = motionEvent.getX();
                        y2 = motionEvent.getY();

                        diffx = x2 - x1;
                        diffy = y2 - y1;
                        if (diffx < 0 & abs(diffx) > abs(diffy)) {
                            //gauche
                            verificationDesAction(ActionRequired.SWIPELEFT,v);
                            relativebutton.setBackgroundColor(Color.parseColor("#995050"));
                            askAndLaunchRandomActions();
                        }
                        if (diffx > 0 & abs(diffx) > abs(diffy)) {
                            //droite
                            verificationDesAction(ActionRequired.SWIPERIGHT,v);
                            relativebutton.setBackgroundColor(Color.parseColor("#509950"));
                            askAndLaunchRandomActions();
                        }
                        if (diffy < 0 & abs(diffy) > abs(diffx)) {
                            //haut
                            verificationDesAction(ActionRequired.SWIPEUP,v);
                            relativebutton.setBackgroundColor(Color.parseColor("#505090"));
                            askAndLaunchRandomActions();
                        }
                        if (diffy > 0 & abs(diffy) > abs(diffx)) {
                            //bas
                            verificationDesAction(ActionRequired.SWIPEDOWN,v);
                            relativebutton.setBackgroundColor(Color.parseColor("#995099"));
                            askAndLaunchRandomActions();
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        }));

        //swipelistener = new SwipeListener(relativeLayout);

        /**
         * Preparation du jeu terminé, lancement de la partie
         */
        startGame();
    }

/*
    public class SwipeListener implements View.OnTouchListener{
        GestureDetector gestureDetector;
        //constructeur
        SwipeListener(View view){
            int threshold = 100;
            int velocity_threshold = 100;
            GestureDetector.SimpleOnGestureListener listener =
                    new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onDown(MotionEvent e) {

                            return true;
                        }
                        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY, View v) {
                            float xDiff = e2.getX() - e1.getX();
                            float yDiff = e2.getY() - e1.getY();
                            try{
                                if(Math.abs(xDiff)>Math.abs(yDiff)){
                                    if(Math.abs(xDiff)>threshold && Math.abs(velocityX)>velocity_threshold){
                                        if (xDiff > 0) {
                                            actionRequiredText.setText("swiped rifht");
                                            verificationDesAction(ActionRequired.SWIPERIGHT,v);
                                        }
                                        if(xDiff < 0) {
                                            actionRequiredText.setText("swiped left");

                                            verificationDesAction(ActionRequired.SWIPELEFT,v);
                                        }
                                    }
                                    return true;
                                }
                                else{
                                    if(Math.abs(yDiff)>threshold && Math.abs(velocityY)>velocity_threshold){
                                        if (yDiff > 0) {
                                            verificationDesAction(ActionRequired.SWIPEDOWN,v);
                                        }
                                        //}
                                        if(yDiff < 0){
                                            verificationDesAction(ActionRequired.SWIPEUP,v);
                                        }
                                    }
                                    return true;
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            return false;
                        }
                    };
            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent((motionEvent));
        }
*/


  //  }


















    /**
     * Lancement de la partie, lance le premier timer et la première activité au hasard
     *
     */
    private void startGame() {
        CountDownText = findViewById(R.id.countdowm_timer);
        actionRequiredText = findViewById(R.id.AskedAction);
        timerIsRunning = true;
        startTimer();
        //lancement timer
        timer=232;
       askAndLaunchRandomActions(); // les actions utilisateur déterminerons si on relance une actiondemandé
    }

    public void pauseMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Creation du timer et le lance
     */
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

    /**
     * mettre a jour le countdown timer
     */
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

    /**
     *selection et lancement de la prochaine action du jeu
     */
    public void askAndLaunchRandomActions(){
        int random = new Random().nextInt(9) + 1; // from 1 to 1 (random 1 = 0)
       //selon le random généré, choisi une fonction
        switch(random) {
            case 1:
                bopItActionCENTRE();//demande de pousser le bouton
                break;
            case 2:
                bopItActionHAUTGAUCHE();
                break;
            case 3:
                bopItActionHAUTDROITE();
                break;
            case 4:
                bopItActionBASGAUCHE();
                break;
            case 5:
                bopItActionBASDROITE();
                break;
            case 6:
                AskForSlideAction(ActionRequired.SWIPEUP);
                break;
            case 7:
                AskForSlideAction(ActionRequired.SWIPEDOWN);

                break;
            case 8:
                AskForSlideAction(ActionRequired.SWIPELEFT);

                break;
            case 9:
                AskForSlideAction(ActionRequired.SWIPERIGHT);
                break;
            default: // bop-it
                break;
                // code block
        }
    }

    /**
     * Set slide comme action requise
     */
    public void AskForSlideAction(ActionRequired actionrequiredlocal){
        actionRequired = actionrequiredlocal;
        switch (actionrequiredlocal){
            case SWIPEUP:
                actionRequiredText.setText("Slide up!");
                break;
            case SWIPEDOWN:
                actionRequiredText.setText("Slide down!");
                break;
            case SWIPELEFT:
                actionRequiredText.setText("Slide left!");
                break;
            case SWIPERIGHT:
                actionRequiredText.setText("Slide right!");
                break;
            default:
                break;
        }
    }

    /**
     * Fonction bouton lorsque la fonction de hasard choisi l'un des boutons est choisi
     * elles remettent le bouton visible et lui met un OnClickListener pour verifier l'action lorsque cliquer
     */
    public void bopItActionCENTRE(){
        actionRequiredText.setText("Bop-IT!");
        Button bopItButtonCentral = (Button)findViewById(R.id.bopITButtonCentral); //todo trouver bouton
        bopItButtonCentral.setVisibility(View.VISIBLE);

        bopItButtonCentral.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (verificationDesAction(ActionRequired.BOPITCENTRE,v) ){
                    bopItButtonCentral.setVisibility(View.GONE);
                    askAndLaunchRandomActions();
                }
            }
        });

        actionRequired = ActionRequired.BOPITCENTRE;
    }
    public void bopItActionHAUTGAUCHE(){
        actionRequiredText.setText("Bop-IT!");
        Button bopItButtonHautGauche = (Button)findViewById(R.id.bopITButtonHautGauche);
        bopItButtonHautGauche.setVisibility(View.VISIBLE);
        bopItButtonHautGauche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (verificationDesAction(ActionRequired.BOPITHAUTGAUCHE,v) ){
                    bopItButtonHautGauche.setVisibility(View.GONE);
                    askAndLaunchRandomActions();
                }
            }
        });
        actionRequired = ActionRequired.BOPITHAUTGAUCHE;
    }
    public void bopItActionHAUTDROITE(){
        actionRequiredText.setText("Bop-IT!");
        Button bopItButtonHautDroite = (Button)findViewById(R.id.bopITButtonHautDroite);
        bopItButtonHautDroite.setVisibility(View.VISIBLE);

        bopItButtonHautDroite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (verificationDesAction(ActionRequired.BOPITHAUTDROITE,v)){
                    bopItButtonHautDroite.setVisibility(View.GONE);
                    askAndLaunchRandomActions();
                }
            }
        });
        actionRequired=ActionRequired.BOPITHAUTDROITE;
    }
    public void bopItActionBASGAUCHE(){
        actionRequiredText.setText("Bop-IT!");
        Button bopItButtonBasGauche = (Button)findViewById(R.id.bopITButtonBasGauche);
        bopItButtonBasGauche.setVisibility(View.VISIBLE);

        bopItButtonBasGauche.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (verificationDesAction(ActionRequired.BOPITBASGAUCHE,v) ){
                    bopItButtonBasGauche.setVisibility(View.GONE);
                    askAndLaunchRandomActions();
                }
            }
        });
        actionRequired = ActionRequired.BOPITBASGAUCHE;
    }
    public void bopItActionBASDROITE(){
        actionRequiredText.setText("Bop-IT!");
        Button bopItButtonBasdroite = (Button)findViewById(R.id.BopITButtonBasDroite);
        bopItButtonBasdroite.setVisibility(View.VISIBLE);

        findViewById(R.id.BopITButtonBasDroite).setVisibility(View.VISIBLE);
        bopItButtonBasdroite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (verificationDesAction(ActionRequired.BOPITBASDROITE,v) ){
                    bopItButtonBasdroite.setVisibility(View.GONE);
                    askAndLaunchRandomActions();
                }
            }
        });
        actionRequired = ActionRequired.BOPITBASDROITE;

    }
    /**
     * Verification si l'action est
     * @param actionhasard type de l'action réalisé
     * @param v view
     * @return true si l'action est la bonne, false si laction realise est mauvaise
     */
    public boolean verificationDesAction(ActionRequired actionhasard, View v){
        if (actionRequired == actionhasard){
            actionSucceed++;
            return true;
        }
        else{
            gameOver(v);
            return false;
        }
    }
    /**
     * game over, transfert du score à la prochaine activité
     * @param view
     */
    public void gameOver(View view) {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("pseudo",pseudo);
        intent.putExtra("timer",timer);
        startActivity(intent);
    }
}
