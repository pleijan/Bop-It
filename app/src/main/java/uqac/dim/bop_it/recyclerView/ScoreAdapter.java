package uqac.dim.bop_it.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uqac.dim.bop_it.R;
import uqac.dim.bop_it.data.Score;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreViewHolder> {

    // FOR DATA
    private List<Score> scores;

    // CONSTRUCTOR
    public ScoreAdapter(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.score_item, parent, false);

        return new ScoreViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(ScoreViewHolder viewHolder, int position) {
        viewHolder.updateWithScore(this.scores.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.scores.size();
    }
}