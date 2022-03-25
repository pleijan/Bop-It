package uqac.dim.bop_it.recyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import uqac.dim.bop_it.R;
import uqac.dim.bop_it.data.Score;

public class ScoreViewHolder extends RecyclerView.ViewHolder {



    public ScoreViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);


    }

    public void updateWithScore(Score score) {
        ((TextView) itemView.findViewById(R.id.fragment_main_item_title)).setText(score.toString());
    }
}
