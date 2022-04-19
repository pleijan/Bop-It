package uqac.dim.bop_it;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.List;

import uqac.dim.bop_it.data.Score;
import uqac.dim.bop_it.data.ScoreBD;

public class GameOverActivity extends AppCompatActivity {

    String pseudo;
    int timer;
    private ScoreBD sbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fin_de_partie);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pseudo = extras.getString("pseudo");
            timer = extras.getInt("timer");
            Log.i("DIM", pseudo+","+timer);
        }

        ((TextView) findViewById(R.id.timerFinal)).setText(String.valueOf(timer));

        sbd = ScoreBD.getDatabase(getApplicationContext());
        sbd.scoreDao().addScore(new Score(pseudo,timer));
        List<Score> scores = sbd.scoreDao().getAllScore();

        GridLayout grille = findViewById(R.id.grille_score);


        Iterator<Score> it = scores.iterator();

        while(it.hasNext()) {

            TextView pseudoView = new TextView(this);
            TextView scoreView = new TextView(this);

            Score score = it.next();

            pseudoView.setText(score.getPseudo());
            scoreView.setText(String.valueOf(score.getNbEpreuve()));
            pseudoView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            pseudoView.setWidth(200);

            scoreView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

            grille.addView(pseudoView);
            grille.addView(scoreView);

        }
    }

    public void PlayAgain(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("pseudo",pseudo);
        startActivity(intent);
    }

    public void backMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
