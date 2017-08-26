package com.astro.programguide.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astro.programguide.R;
import com.astro.programguide.data.models.Channel;
import com.astro.programguide.data.models.Event;
import com.astro.programguide.data.models.OnLoadMoreListener;
import com.astro.programguide.util.Utils;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hitesh on 8/22/17.
 */

public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean            isLoading;
    private int                visibleThreshold = 5;
    private int                lastVisibleItem, totalItemCount;
    private OnLoadMoreListener onLoadMoreListener;

    Context                    context;
    ArrayList<Channel>         channels;

    ArrayList<Event>           events;

    public EventAdapter(RecyclerView recyclerView, Context context, ArrayList<Event> events) {
        this.channels = channels;
        this.events = events;
        this.context = context;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tv_guide_row, parent, false);
        return new EventViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        EventViewHolder eventViewHolder = (EventViewHolder) holder;
        eventViewHolder.channelName.setText(events.get(position).getChannelTitle());
        eventViewHolder.channelNumber.setText("CH "+events.get(position).getChannelStbNumber());
        eventViewHolder.showName.setText(events.get(position).getProgrammeTitle());
        try {
            eventViewHolder.showTime.setText(Utils.convertDateToTime(events.get(position).getDisplayDateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (events != null && events.size() > 0)
            return events.size();
        return 0;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_channel_name)
        TextView channelName;
        @BindView(R.id.show_title)
        TextView showName;
        @BindView(R.id.show_time)
        TextView showTime;
        @BindView(R.id.tv_channel_number)
        TextView channelNumber;

        public EventViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }

    public void setLoaded() {
        isLoading = false;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

}
