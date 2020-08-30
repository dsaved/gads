package com.dsaved.gadsleaderboard.recyclerview_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dsaved.gadsleaderboard.R;
import com.dsaved.gadsleaderboard.RecyclerView_OnClickListener;

public class RecyclerView_SkillIQ_Holder extends RecyclerView.ViewHolder implements
        View.OnClickListener{
    // View holder for item recycler view as we used in listview
    public TextView name, content;
    public ImageView badge;
    public LinearLayout itemTopLearnersLayout;

    private RecyclerView_OnClickListener.OnClickListener onClickListener;

    public RecyclerView_SkillIQ_Holder(View view) {
        super(view);
        // Find all views ids
        this.name = view.findViewById(R.id.name);
        this.content = view.findViewById(R.id.content);
        this.badge = view.findViewById(R.id.badge);
        this.itemTopLearnersLayout = view.findViewById(R.id.itemTopLearnersLayout);

        this.itemTopLearnersLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // setting custom listener
        if (onClickListener != null) {
            onClickListener.OnItemClick(v, getAdapterPosition());
        }
    }

    // Setter for listener
    public void setClickListener(
            RecyclerView_OnClickListener.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
