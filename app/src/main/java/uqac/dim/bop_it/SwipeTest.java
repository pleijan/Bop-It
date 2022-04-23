package uqac.dim.bop_it;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    TextView textView;
    SwipeListener swipelistener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.relativelayout);
        textView = findViewById(R.id.text_view);

        swipelistener = new SwipeListener(relativeLayout);


    }

    private class SwipeListener implements View.OnTouchListener{
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

                        @Override
                        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                            float xDiff = e2.getX() - e1.getX();
                            float yDiff = e2.getY() - e1.getY();
                            List<String> list1 = new ArrayList<>();
                            list1.add("Swipe Top");
                            list1.add("Swipe Bottom");
                            list1.add("Swipe Left");
                            list1.add("Swipe Right");
                            Random rand = new Random();
                            String actionSwipe = list1.get(rand.nextInt(list1.size()));
                            textView.setText(actionSwipe);
                            try{
                                if(Math.abs(xDiff)>Math.abs(yDiff)){
                                    if(Math.abs(xDiff)>threshold && Math.abs(velocityX)>velocity_threshold){
                                        //if(actionSwipe.contains("Swipe Right")){
                                        if (xDiff > 0) {
                                            showToast("Swiped right");
                                        }
                                        //}
                                        // if(actionSwipe.contains("Swipe Left"))  {
                                        if(xDiff < 0) {
                                            showToast("Swiped left");
                                        }
                                    }
                                    return true;
                                }
                                // }
                                else{
                                    if(Math.abs(yDiff)>threshold && Math.abs(velocityY)>velocity_threshold){
                                        //if(actionSwipe.contains("Swipe Down")) {
                                        if (yDiff > 0) {
                                            showToast("Swiped Down");
                                        }
                                        //}
                                        // if(actionSwipe.contains("Swipe Top")){
                                        if(yDiff > 0){
                                            showToast( "Swiped top");
                                        }
                                    }
                                    return true;
                                }
                                //}
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


    }
    public void showToast( String message ){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}