package com.astro.programguide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astro.programguide.R;
import com.astro.programguide.data.models.Channel;
import com.astro.programguide.data.models.RecyclerViewClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hitesh on 8/20/17.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {

    ArrayList<Channel>        channels;
    Context                   context;
    RecyclerViewClickListener recyclerViewClickListener;

    public ChannelAdapter(Context context, ArrayList<Channel> channels, RecyclerViewClickListener recyclerViewClickListener) {
        this.channels = channels;
        this.context = context;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public ChannelAdapter.ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_row, parent, false);
        return new ChannelViewHolder(itemView, recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(ChannelAdapter.ChannelViewHolder holder, int position) {
        holder.channelName.setText(channels.get(position).getChannelTitle());
        holder.channelNumber.setText("CH " + channels.get(position).getChannelStbNumber());
        if (channels.get(position).isFavourite()) {
            holder.favIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_black));
        } else {
            holder.favIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_border_black));

        }
    }

    @Override
    public int getItemCount() {
        if (channels != null && channels.size() > 0)
            return channels.size();
        else
            return 0;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;

    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_channel_name)
        TextView                          channelName;
        @BindView(R.id.icon_fav)
        ImageView                         favIcon;
        @BindView(R.id.tv_channel_number)
        TextView                          channelNumber;
        private RecyclerViewClickListener mListener;

        boolean                           isFavourite;

        public ChannelViewHolder(View itemView, RecyclerViewClickListener clickListener) {
            super(itemView);
            mListener = clickListener;
            ButterKnife.bind(this, itemView);
            favIcon.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.icon_fav:
                    mListener.click(v, getAdapterPosition());
            }
        }
    }

}
