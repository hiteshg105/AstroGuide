package com.astro.programguide.util.contracts;

import com.astro.programguide.data.models.Channel;

import java.util.ArrayList;

/**
 * Created by hitesh on 8/20/17.
 */

public class ChannelContract {

   public interface View {

        void showChannels(ArrayList<Channel> channelResponse);

        void showProgress();

        void hideProgress();

        void showErrorMessage();

    }

    public interface Presenter {

        void getChannels();
    }
}
