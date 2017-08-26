package com.astro.programguide.network;

import com.astro.programguide.data.models.ChannelResponse;
import com.astro.programguide.data.models.EventResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by hitesh on 8/20/17.
 */

public interface NetworkStores {
    @GET("/ams/v3/getChannelList")
    Call<ChannelResponse> getChannels();

    @GET("/ams/v3/getEvents")
    Call<EventResponse> getChannelEvent(@QueryMap(encoded = true) Map<String, Object> options);

}
