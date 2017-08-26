package com.astro.programguide.data.models;

/**
 * Created by hitesh on 8/20/17.
 */

public class Channel {

    int     channelId;
    String  channelTitle;

    int     channelStbNumber;
    boolean isFavourite;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public int getChannelStbNumber() {
        return channelStbNumber;
    }

    public void setChannelStbNumber(int channelStbNumber) {
        this.channelStbNumber = channelStbNumber;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Override
    public String toString() {
        return "Channel{" + "channelId=" + channelId + ", channelTitle='" + channelTitle + '\'' + ", channelStbNumber=" + channelStbNumber + ", isFavourite=" + isFavourite + '}';
    }
}
