package uqac.dim.bop_it;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Iterator;
import java.util.List;

import uqac.dim.bop_it.data.Score;
import uqac.dim.bop_it.data.ScoreBD;

public class LeaderboardActivity extends AppCompatActivity {

    private ScoreBD sbd;
    private GridLayout grille;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.leaderboard);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        sbd = ScoreBD.getDatabase(getApplicationContext());

        List<Score> scores = sbd.scoreDao().getAllScore();

        Log.i("DIM",scores.toString());

        GridLayout grille = findViewById(R.id.grille_score);


        Iterator<Score> it = scores.iterator();

        while(it.hasNext()) {

            TextView pseudoView = new TextView(this);
            TextView scoreView = new TextView(this);

            Score score = it.next();

            pseudoView.setText(score.getPseudo());
            scoreView.setText(String.valueOf(score.getNbEpreuve()));
            pseudoView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            scoreView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            grille.addView(pseudoView);
            grille.addView(scoreView);

        }
    }

    public void deleteScore(View view){
        sbd.scoreDao().deleteAllScore();
    }

    public void backMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
