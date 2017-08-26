package com.astro.programguide.data.Comparators;

import com.astro.programguide.data.models.Channel;

import java.util.Comparator;

/**
 * Created by hitesh on 8/21/17.
 */

public class ChannelNameComparator implements Comparator<Channel> {
    @Override
    public int compare(Channel channel1, Channel channel2) {
        return channel1.getChannelTitle().compareTo(channel2.getChannelTitle());
    }
}
