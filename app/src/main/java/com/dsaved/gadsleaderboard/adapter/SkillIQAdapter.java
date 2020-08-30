package com.dsaved.gadsleaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dsaved.gadsleaderboard.R;
import com.dsaved.gadsleaderboard.RecyclerView_OnClickListener;
import com.dsaved.gadsleaderboard.model.SkillIQModel;
import com.dsaved.gadsleaderboard.recyclerview_holder.RecyclerView_SkillIQ_Holder;
import com.dsaved.gadsleaderboard.utils.Navigator;

import java.util.ArrayList;

public class SkillIQAdapter extends RecyclerView.Adapter<RecyclerView_SkillIQ_Holder> {

    private ArrayList<SkillIQModel> topLearnersItem;
    private Context context;
    private Navigator navigator;

    public SkillIQAdapter(FragmentActivity mContext) {
        this.context = mContext;
        navigator = new Navigator(context);
    }

    public void addItems(ArrayList<SkillIQModel> eventsDatamodel) {
        this.topLearnersItem = eventsDatamodel;
    }

    @Override
    public int getItemCount() {
        return (null != topLearnersItem ? topLearnersItem.size() : 0);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_SkillIQ_Holder holder, int position) {
        // Now in this method the items will set and click listener will occur
        final SkillIQModel model = topLearnersItem.get(position);

        // setting data over views
        holder.name.setText(model.getName());
        String data = model.getScore() + " Skill IQ Score, " + model.getCountry();
        holder.content.setText(data);
        Glide.with(context)
                .load(model.getBadgeUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.badge);

        // Implementing click listener
        holder.setClickListener(new RecyclerView_OnClickListener.OnClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.itemTopLearnersLayout:

                        break;
                }
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView_SkillIQ_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // This method will inflate the layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.skilliqitem, viewGroup, false);
        return new RecyclerView_SkillIQ_Holder(mainGroup);
    }
}