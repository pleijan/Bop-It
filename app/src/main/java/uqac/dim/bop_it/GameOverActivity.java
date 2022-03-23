package uqac.dim.bop_it;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    String pseudo,timer;

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
            timer = extras.getString("timer");
            Log.i("DIM", pseudo+","+timer);
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
