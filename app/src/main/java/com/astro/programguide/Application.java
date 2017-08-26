package com.astro.programguide;

import com.astro.programguide.dagger.component.ApplicationComponent;
import com.astro.programguide.dagger.component.DaggerApplicationComponent;
import com.astro.programguide.dagger.module.ApplicationModule;
import com.astro.programguide.dagger.module.DaoModule;
import com.astro.programguide.dagger.module.ManagerModule;

/**
 * Created by hitesh on 8/20/17.
 */

public class Application  extends android.support.multidex.MultiDexApplication  {

    private static Application   instance;
    private ApplicationComponent applicationComponent;

    public static synchronized Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).daoModule(new DaoModule(this)).managerModule(
                new ManagerModule(this)).build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
