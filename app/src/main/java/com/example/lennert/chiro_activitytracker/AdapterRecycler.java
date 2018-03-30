package com.example.lennert.chiro_activitytracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



/**
 * Created by Lennert on 21/03/2018.
 */

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder>{

    private List<RecyclerItem> recyclerItems;
    private Context context;

    final private ListItemClickListener mOnClickListener;

    public AdapterRecycler(List<RecyclerItem> recyclerItems, Context context, ListItemClickListener Listener) {
        this.recyclerItems = recyclerItems;
        this.context = context;
        this.mOnClickListener = Listener;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public AdapterRecycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.saturday_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterRecycler.ViewHolder holder, int position) {
        RecyclerItem recyclerItem = recyclerItems.get(position);

        holder.mSaturdayTextView.setText(recyclerItem.getSaturdayDate());
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView mSaturdayTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mSaturdayTextView = itemView.findViewById(R.id.tv_saturday);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);


        }
    }


}