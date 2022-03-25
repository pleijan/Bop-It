package uqac.dim.bop_it;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

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

        //lancement timer

        timer="0:30";


            // while il reste du temps
            // choisi une epreuve au hasard
            //
            // si reussi calcul nouveau timer avec le nombre d'epreuve reussi et restart timer
            // sinon fin du jeu (gameOver(view View))



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
}
