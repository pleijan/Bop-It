package uqac.dim.bop_it;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SwipeTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SwipeTest extends Fragment implements View.OnTouchListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SwipeCallback swipeCallback;
    private SwipeSingleCallback swipeSingleCallback;
    private SwipeDirection detectSwipeDirection;

    float x1, x2, y1, y2;
    View view;

    private static SwipeTest newInstance(){
        return new SwipeTest();
    }

    public static void detect( View view, SwipeTest.SwipeCallback swipeCallback  ){
        SwipeTest evt = SwipeTest.newInstance();
        evt.swipeCallback = swipeCallback;
        evt.view = view;
        evt.detect();
    }

    public static void detectTop( View view, SwipeTest.SwipeSingleCallback swipeSingleCallback){
        SwipeTest evt = SwipeTest.newInstance();
        evt.swipeSingleCallback = swipeSingleCallback;
        evt.view = view;
        evt.detectSingle(SwipeTest.SwipeDirection.TOP);
    }

    public static void detectRight( View view, SwipeTest.SwipeSingleCallback swipeSingleCallback){
        SwipeTest evt = SwipeTest.newInstance();
        evt.swipeSingleCallback = swipeSingleCallback;
        evt.view = view;
        evt.detectSingle(SwipeTest.SwipeDirection.RIGHT);
    }

    public static void detectBottom( View view, SwipeTest.SwipeSingleCallback swipeSingleCallback){
        SwipeTest evt = SwipeTest.newInstance();
        evt.swipeSingleCallback = swipeSingleCallback;
        evt.view = view;
        evt.detectSingle(SwipeTest.SwipeDirection.BOTTOM);
    }
    public static void detectLeft( View view, SwipeTest.SwipeSingleCallback swipeSingleCallback){
        SwipeTest evt = SwipeTest.newInstance();
        evt.swipeSingleCallback = swipeSingleCallback;
        evt.view = view;
        evt.detectSingle(SwipeTest.SwipeDirection.LEFT);
    }

    private void detect( ){
        view.setOnTouchListener(this);
    }

    private void detectSingle( SwipeTest.SwipeDirection direction ){
        this.detectSwipeDirection = direction;
        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() ){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                SwipeTest.SwipeDirection direction =null;

                float xDiff = x2-x1;
                float yDiff = y2- y1;
                if( Math.abs(xDiff) > Math.abs(yDiff) ){
                    if (x1<x2){
                        direction = SwipeTest.SwipeDirection.RIGHT;
                    }else{
                        direction = SwipeTest.SwipeDirection.LEFT;
                    }
                }else {
                    if (y1 > y2) {
                        direction = SwipeTest.SwipeDirection.TOP;
                    } else {
                        direction = SwipeTest.SwipeDirection.BOTTOM;
                    }
                }

                // Only trigger the requested event only if there
                if ( detectSwipeDirection !=null && swipeSingleCallback !=null ){
                    if ( direction == detectSwipeDirection ){
                        swipeSingleCallback.onSwipe();
                    }
                }else{
                    if ( direction == SwipeTest.SwipeDirection.RIGHT ){
                        swipeCallback.onSwipeRight();
                    }
                    if ( direction == SwipeTest.SwipeDirection.LEFT ){
                        swipeCallback.onSwipeLeft();
                    }
                    if ( direction == SwipeTest.SwipeDirection.TOP ){
                        swipeCallback.onSwipeTop();
                    }
                    if ( direction == SwipeTest.SwipeDirection.BOTTOM ){
                        swipeCallback.onSwipeBottom();
                    }
                }

                break;
        }
        return false;
    }

    public enum SwipeDirection{
        TOP, RIGHT, BOTTOM, LEFT
    }

    public interface SwipeCallback{
        public void onSwipeTop();
        public void onSwipeRight();
        public void onSwipeBottom();
        public void onSwipeLeft();
    }

    public interface SwipeSingleCallback{
        public void onSwipe();
    }
    public SwipeTest() {


    }
    // TODO: Rename and change types and number of parameters
    public static SwipeTest newInstance(String param1, String param2) {
        SwipeTest fragment = new SwipeTest();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_swipe_test, container, false);
    }
}