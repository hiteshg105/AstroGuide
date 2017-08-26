package com.astro.programguide.dagger.module;

import android.content.Context;

import com.astro.programguide.database.dao.ChannelDAO;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hitesh on 8/21/17.
 */

@Module
public class DaoModule {

    private Context context;

    public DaoModule(Context context) {
        this.context = context;
    }

    @Provides
    public ChannelDAO providesChannelDAO() {
        return new ChannelDAO(context);
    }

}
