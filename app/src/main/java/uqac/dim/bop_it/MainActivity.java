package uqac.dim.bop_it;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    TextView ActionGyro;
    SensorManager sensorManager;
    Sensor rotationVectorSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_accueil);


        List<String> list1 = new ArrayList<>();
        //list1.add("mettre son portable droit");
        list1.add("tourner à 90° gauche");
        list1.add("tourner à 90° droite");
        list1.add("tourner son portable à l'envers");



        Random rand = new Random();

        String actionGyro = list1.get(rand.nextInt(list1.size()));
        //String actioonGyro =  list1.get(ThreadLocalRandom.current().nextInt(2) % list1.size());
        ActionGyro = findViewById(R.id.ActionGyro);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.d("DIM","Le fragment est bien demarree");

        ActionGyro.setText(actionGyro);

    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(rvListener,
                rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(rvListener);
    }

    public SensorEventListener rvListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor rotationVectorSensor, int acc) {


        }

        public void onSensorChanged(SensorEvent event) {
            float[] rotationMatrix = new float[16];
            SensorManager.getRotationMatrixFromVector(
                    rotationMatrix, event.values);


            // Remap coordinate system
            float[] remappedRotationMatrix = new float[16];
            SensorManager.remapCoordinateSystem(rotationMatrix,
                    SensorManager.AXIS_X,
                    SensorManager.AXIS_Z,
                    remappedRotationMatrix);

            // Convert to orientations
            float[] orientations = new float[3];
            SensorManager.getOrientation(remappedRotationMatrix, orientations);

            //convert radians to degrees
            for(int i = 0; i < 3; i++) {
                orientations[i] = (float)(Math.toDegrees(orientations[i]));
            }

            String textrecup = (String) ActionGyro.getText();

            //cas tourner son portable 90° droite
            if (textrecup.contains("tourner à 90° droite")){
                if(orientations[2] > 80 && orientations[2] < 100) {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    //onStop();
                }
            }
            //cas tourner son portable 90° gauche
            else if (textrecup.contains("tourner à 90° gauche")) {
                if (orientations[2] < -80 && orientations[2] > -100) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
            }

            //cas garder son portable
            /*else if (textrecup.contains("mettre son portable droit")) {
                if (orientations[2] > -10 && orientations[2] < 10) {
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                }
            }*/

            //cas tourner son portable à l'envers
            else if (textrecup.contains("tourner son portable à l'envers")) {
                if (orientations[2] > 170 && orientations[2] < 190) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                }
            }
            else{getWindow().getDecorView().setBackgroundColor(Color.GREEN);}

        }
    };

}