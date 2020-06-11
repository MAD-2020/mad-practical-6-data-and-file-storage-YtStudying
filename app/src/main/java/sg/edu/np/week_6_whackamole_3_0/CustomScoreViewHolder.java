package sg.edu.np.week_6_whackamole_3_0;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomScoreViewHolder extends RecyclerView.ViewHolder {
    /* Hint:
        1. This is a customised view holder for the recyclerView list @ levels selection page
     */
    private static final String FILENAME = "CustomScoreViewHolder.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    TextView level, score;
    View v;

    public CustomScoreViewHolder(final View itemView){
        super(itemView);

        /* Hint:
        This method dictates the viewholder contents and links the widget to the objects for manipulation.
         */

        v = itemView;
        level = itemView.findViewById(R.id.lvl);
        score = itemView.findViewById(R.id.score);

    }
}
