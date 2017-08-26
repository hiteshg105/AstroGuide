package com.astro.programguide.dagger.component;

import android.app.Application;

import com.astro.programguide.dagger.module.ApplicationModule;
import com.astro.programguide.dagger.module.DaoModule;
import com.astro.programguide.dagger.module.ManagerModule;
import com.astro.programguide.dagger.module.PresenterModule;
import com.astro.programguide.manager.ChannelManager;
import com.astro.programguide.manager.EventManager;
import com.astro.programguide.ui.channelView.ChannelActivity;
import com.astro.programguide.ui.channelView.ChannelFragment;
import com.astro.programguide.ui.channelView.EventsFragment;
import com.astro.programguide.util.presenter.ChannelsPresenter;
import com.astro.programguide.util.presenter.EventsPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hitesh on 8/20/17.
 */

@Singleton
@Component(modules = { ApplicationModule.class, PresenterModule.class, DaoModule.class, ManagerModule.class })
public interface ApplicationComponent {
    //Application
    void inject(Application app);

    void inject(ChannelActivity activity);

    void inject(ChannelFragment channelFragment);

    void inject(ChannelManager channelManager);

    void inject(EventManager eventManager);

    void inject(ChannelsPresenter channelsPresenter);

    void inject(EventsPresenter eventsPresenter);

    void inject(EventsFragment eventsFragment);

}
