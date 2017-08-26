package com.astro.programguide.dagger.module;

import android.support.annotation.NonNull;

import com.astro.programguide.util.contracts.ChannelContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hitesh on 8/20/17.
 */

@Module
public class PresenterModule {

    ChannelContract.View channelView;

    public PresenterModule(@NonNull ChannelContract.View view) {
        this.channelView = view;
    }

    @Provides
    ChannelContract.View providesChannelContractView() {
        return channelView;
    }
}
