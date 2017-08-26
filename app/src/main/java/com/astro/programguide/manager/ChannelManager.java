package com.astro.programguide.manager;

import android.content.Context;

import com.astro.programguide.Application;
import com.astro.programguide.data.Comparators.ChannelNameComparator;
import com.astro.programguide.data.Comparators.ChannelNumberComparator;
import com.astro.programguide.data.models.Channel;
import com.astro.programguide.database.dao.ChannelDAO;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Created by hitesh on 8/21/17.
 */

public class ChannelManager {

    Context    context;

    @Inject
    ChannelDAO channelDAO;

    public ChannelManager(Context context) {
        this.context = context;
        Application.getInstance().getApplicationComponent().inject(this);

    }

    public ArrayList<Channel> sortByName(ArrayList<Channel> channels) {
        Collections.sort(channels, new ChannelNameComparator());
        return channels;
    }

    public ArrayList<Channel> sortByNumber(ArrayList<Channel> channels) {
        Collections.sort(channels, new ChannelNumberComparator());
        return channels;
    }

}
