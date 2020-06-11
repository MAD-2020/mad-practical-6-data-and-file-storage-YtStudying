package sg.edu.np.week_6_whackamole_3_0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CustomScoreAdaptor extends RecyclerView.Adapter<CustomScoreViewHolder> {
    /* Hint:
        1. This is the custom adaptor for the recyclerView list @ levels selection page

     */
    private static final String FILENAME = "CustomScoreAdaptor.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    private ArrayList<UserData> lvl_scoreList;
    Context context;

    public CustomScoreAdaptor(ArrayList<UserData> us, Context con){
        /* Hint:
        This method takes in the data and readies it for processing.
         */

        this.context = con;
        this.lvl_scoreList = us;

    }

    public CustomScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        /* Hint:
        This method dictates how the viewholder layuout is to be once the viewholder is created.
         */

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        CustomScoreViewHolder holder = new CustomScoreViewHolder(view);
        return holder;

    }

    public void onBindViewHolder(CustomScoreViewHolder holder, final int position){

        /* Hint:
        This method passes the data to the viewholder upon bounded to the viewholder.
        It may also be used to do an onclick listener here to activate upon user level selections.

        Log.v(TAG, FILENAME + " Showing level " + level_list.get(position) + " with highest score: " + score_list.get(position));
        Log.v(TAG, FILENAME+ ": Load level " + position +" for: " + list_members.getMyUserName());
         */

        UserData us = lvl_scoreList.get(position);
        holder.level.setText(us.getLevels().toString());
        holder.score.setText(us.getScores().toString());
        final String lvlSelection = holder.level.getText().toString();
        final String highScore = holder.score.getText().toString();

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public int getItemCount(){
        /* Hint:
        This method returns the the size of the overall data.
         */

        return lvl_scoreList.size();

    }
}