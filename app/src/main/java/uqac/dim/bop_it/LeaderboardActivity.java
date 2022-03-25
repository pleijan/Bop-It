package uqac.dim.bop_it;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import uqac.dim.bop_it.data.Score;
import uqac.dim.bop_it.data.ScoreBD;

public class LeaderboardActivity extends AppCompatActivity {

    private ScoreBD sbd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        sbd = ScoreBD.getDatabase(getApplicationContext());

        List<Score> scores = sbd.scoreDao().getAllScore();

        Log.i("DIM",scores.toString());

    }

    public void deleteScore(View view){
        sbd.scoreDao().deleteAllScore();
    }

    public void backMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
