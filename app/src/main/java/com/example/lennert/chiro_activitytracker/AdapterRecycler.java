package com.example.lennert.chiro_activitytracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lennert on 21/03/2018.
 */

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.NumberViewHolder>{
    private static final String TAG = AdapterRecycler.class.getSimpleName();

    private int mNumberItems;

    /**
     * Constructor for AdapterRecycler that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfItems Number of items to display in list
     */
    public AdapterRecycler(int numberOfItems) {
        mNumberItems = numberOfItems;
    }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    // COMPLETED (12) Create a class called NumberViewHolder that extends RecyclerView.ViewHolder
    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder extends RecyclerView.ViewHolder {

        // COMPLETED (13) Within NumberViewHolder, create a TextView variable called listItemNumberView
        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;

        // COMPLETED (14) Create a constructor for NumberViewHolder that accepts a View called itemView as a parameter
        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         *                 {@link GreenAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(View itemView) {
            // COMPLETED (15) Within the constructor, call super(itemView) and then find listItemNumberView by ID
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.saturday_list_number);
        }

        // COMPLETED (16) Within the NumberViewHolder class, create a void method called bind that accepts an int parameter called listIndex
        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex) {
            // COMPLETED (17) Within bind, set the text of listItemNumberView to the listIndex
            // COMPLETED (18) Be careful to get the String representation of listIndex, as using setText with an int does something different
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }
}
