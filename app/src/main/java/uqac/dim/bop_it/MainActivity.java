package uqac.dim.bop_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import uqac.dim.bop_it.data.ScoreBD;

public class MainActivity extends AppCompatActivity {

    private ScoreBD sbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_accueil);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


    }


    public void Play(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("pseudo",((EditText)findViewById(R.id.pseudo)).getText().toString());
        startActivity(intent);
    }

    public void Leaderboard(View view){
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
}