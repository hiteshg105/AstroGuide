package com.astro.programguide.util.contracts;

import com.astro.programguide.data.models.Channel;
import com.astro.programguide.data.models.Event;

import java.util.ArrayList;

/**
 * Created by hitesh on 8/22/17.
 */

public class EventsContract {

    public interface View {

        void showChannels(ArrayList<Channel> channelResponse);

        void showTVGuide(ArrayList<Event> guideResponse);

        void showProgress();

        void hideProgress();

        void showErrorMessage();

    }

    public interface Presenter {

        void getTvChannels();

        void getTVGuide(ArrayList<String> channelIds);
    }
}
