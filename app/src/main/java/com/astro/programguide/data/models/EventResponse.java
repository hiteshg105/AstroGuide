package com.astro.programguide.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hitesh on 8/22/17.
 */

public class EventResponse {

    @SerializedName("getevent")
    private List<Event> eventList;

    @SerializedName("responseMessage")
    private String      responseMessage;
    @SerializedName("responseCode")
    private int         responseCode;

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
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

    @Override
    public String toString() {
        return "EventResponse{" + "eventList=" + eventList + ", responseMessage='" + responseMessage + '\'' + ", responseCode=" + responseCode + '}';
    }

}
