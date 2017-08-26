package com.astro.programguide.util.presenter;

import com.astro.programguide.Application;
import com.astro.programguide.data.models.Channel;
import com.astro.programguide.data.models.ChannelResponse;
import com.astro.programguide.database.dao.ChannelDAO;
import com.astro.programguide.manager.ChannelManager;
import com.astro.programguide.network.NetworkClient;
import com.astro.programguide.network.NetworkStores;
import com.astro.programguide.util.contracts.ChannelContract;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hitesh on 8/20/17.
 */

public class ChannelsPresenter implements ChannelContract.Presenter {

    ChannelContract.View view;
    NetworkStores        stores;
    @Inject
    ChannelDAO           channelDAO;

    @Inject
    ChannelManager       channelManager;

    public ChannelsPresenter(ChannelContract.View view) {
        this.view = view;
        stores = NetworkClient.getRetrofit().create(NetworkStores.class);
        Application.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public void getChannels() {
        view.showProgress();
        Call<ChannelResponse> call = stores.getChannels();
        final ArrayList<Channel> favouriteChannels = channelDAO.getFavouriteChannels();
        //Hashmap to make time complexity O(n);
        HashMap<Integer, Channel> channelHashMap = new HashMap<>();
        for (Channel channel : favouriteChannels) {
            channelHashMap.put(channel.getChannelId(), channel);
        }
        final HashMap<Integer, Channel> finalChannelHashMap = channelHashMap;
        call.enqueue(new Callback<ChannelResponse>() {
            @Override
            public void onResponse(Call<ChannelResponse> call, Response<ChannelResponse> response) {
                ArrayList<Channel> channels = (ArrayList<Channel>) response.body().getChannels();
                for (Channel channel : channels) {
                    if (finalChannelHashMap.containsKey(channel.getChannelId())) {
                        channel.setFavourite(true);
                    }
                }
                view.hideProgress();
                view.showChannels(channels);
            }

            @Override
            public void onFailure(Call<ChannelResponse> call, Throwable t) {
                view.hideProgress();
                view.showErrorMessage();

            }
        });
    }

}
