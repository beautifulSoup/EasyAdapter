package cn.campusapp.easyadpter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by kris on 16/1/21.
 */
public interface AdapterDelegate <T extends RecyclerView.ViewHolder, U> {
    T onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * @param holder The View Holder
     * @param item The item object
     */
    void onBindViewHolder(T holder, U item);

    int getItemViewType(int position);

}
