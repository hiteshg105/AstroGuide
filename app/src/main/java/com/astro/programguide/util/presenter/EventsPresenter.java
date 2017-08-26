package com.astro.programguide.util.presenter;

import android.util.Log;

import com.astro.programguide.Application;
import com.astro.programguide.data.models.Channel;
import com.astro.programguide.data.models.ChannelResponse;
import com.astro.programguide.data.models.Event;
import com.astro.programguide.data.models.EventResponse;
import com.astro.programguide.manager.EventManager;
import com.astro.programguide.network.NetworkClient;
import com.astro.programguide.network.NetworkStores;
import com.astro.programguide.util.Utils;
import com.astro.programguide.util.contracts.EventsContract;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hitesh on 8/22/17.
 */

public class EventsPresenter implements EventsContract.Presenter {

    EventsContract.View view;
    NetworkStores        stores;

    @Inject
    EventManager         eventManager;

    public EventsPresenter(EventsContract.View view) {
        this.view = view;
        stores = NetworkClient.getRetrofit().create(NetworkStores.class);
        Application.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public void getTvChannels() {
        view.showProgress();
        Call<ChannelResponse> call = stores.getChannels();
        call.enqueue(new Callback<ChannelResponse>() {
            @Override
            public void onResponse(Call<ChannelResponse> call, Response<ChannelResponse> response) {
                ArrayList<Channel> channels = (ArrayList<Channel>) response.body().getChannels();
                view.showChannels(channels);

            }

            @Override
            public void onFailure(Call<ChannelResponse> call, Throwable t) {
                view.hideProgress();
                view.showErrorMessage();
            }
        });
    }

    @Override
    public void getTVGuide(ArrayList<String> channelIds) {
        Map<String, Object> map = eventManager.getCurrentLiveEventMap(channelIds);
        Call<EventResponse> call = stores.getChannelEvent(map);
        call.enqueue(new Callback<EventResponse>() {

            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                Log.d(Utils.TAG, String.valueOf(response.body()));
                ArrayList<Event> events = eventManager.getCurrentAiredShow((ArrayList<Event>) response.body().getEventList());
                view.hideProgress();
                view.showTVGuide(events);
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                view.hideProgress();
                view.showErrorMessage();
            }
        });
    }

}
