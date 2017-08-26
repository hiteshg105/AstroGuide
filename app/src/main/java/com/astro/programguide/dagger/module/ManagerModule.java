package com.astro.programguide.dagger.module;

import android.content.Context;

import com.astro.programguide.manager.ChannelManager;
import com.astro.programguide.manager.EventManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hitesh on 8/21/17.
 */

@Module
public class ManagerModule {

    private Context context;

    public ManagerModule(Context context) {
        this.context = context;
    }

    @Provides
    public ChannelManager providesChannelManager() {
        return new ChannelManager(context);
    }

    @Provides
    public EventManager providesEventManager() {
        return new EventManager(context);
    }

}
