package uqac.dim.bop_it.recyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import uqac.dim.bop_it.R;

public class MainFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout of MainFragment
        View result=inflater.inflate(R.layout.fragment_main, container, false);

        return result;
    }

    @Override
    public void onClick(View view) {

    }

    // --------------
    // ACTIONS
    // --------------

}