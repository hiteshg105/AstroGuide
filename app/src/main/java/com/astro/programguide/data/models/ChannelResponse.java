package com.astro.programguide.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hitesh on 8/20/17.
 */

public class ChannelResponse {

    @SerializedName("channels")
    private List<Channel> channels;
    @SerializedName("responseMessage")
    private String        responseMessage;
    @SerializedName("responseCode")
    private int           responseCode;

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
