package com.dsaved.gadsleaderboard.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dsaved.gadsleaderboard.R;
import com.dsaved.gadsleaderboard.RecyclerView_OnClickListener;
import com.dsaved.gadsleaderboard.model.TopLearnersModel;
import com.dsaved.gadsleaderboard.recyclerview_holder.RecyclerView_TopLearners_Holder;
import com.dsaved.gadsleaderboard.utils.Navigator;

import java.util.ArrayList;

public class TopLearnersAdapter extends RecyclerView.Adapter<RecyclerView_TopLearners_Holder> {

    private ArrayList<TopLearnersModel> topLearnersItem;
    private Context context;
    private Navigator navigator;

    public TopLearnersAdapter(FragmentActivity mContext) {
        this.context = mContext;
        navigator = new Navigator(context);
    }

    public void addItems(ArrayList<TopLearnersModel> eventsDatamodel) {
        this.topLearnersItem = eventsDatamodel;
    }

    @Override
    public int getItemCount() {
        return (null != topLearnersItem ? topLearnersItem.size() : 0);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_TopLearners_Holder holder, int position) {
        // Now in this method the items will set and click listener will occur
        final TopLearnersModel model = topLearnersItem.get(position);

        // setting data over views
        holder.name.setText(model.getName());
        String data = model.getHours() + " learning hours, " + model.getCountry();
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
    public RecyclerView_TopLearners_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // This method will inflate the layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.toplearneritem, viewGroup, false);
        return new RecyclerView_TopLearners_Holder(mainGroup);
    }
}