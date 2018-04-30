package com.example.lennert.chiro_activitytracker.programActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lennert.chiro_activitytracker.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Lennert on 30/04/2018.
 */

public class AdapterRecycler2 extends RecyclerView.Adapter<AdapterRecycler2.ViewHolder>{

    private List<RecyclerItem2> recyclerItem2s;
    private Context context;

    public AdapterRecycler2(List<RecyclerItem2> recyclerItem2s, Context context) {
        this.recyclerItem2s = recyclerItem2s;
        this.context = context;
    }

    @Override
    public AdapterRecycler2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.program_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterRecycler2.ViewHolder holder, int position) {
        RecyclerItem2 recyclerItem = recyclerItem2s.get(position);

        holder.mBranch.setText(recyclerItem.getBranch());
        holder.mProgram.setText(recyclerItem.getProgram());
    }

    @Override
    public int getItemCount() {
        return recyclerItem2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mBranch;
        public TextView mProgram;

        public ViewHolder(View itemView) {
            super(itemView);

            mBranch = itemView.findViewById(R.id.tv_branch);
            mProgram = itemView.findViewById(R.id.tv_program);
        }
    }

}
