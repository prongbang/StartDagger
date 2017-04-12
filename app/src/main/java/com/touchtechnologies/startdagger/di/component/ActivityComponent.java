package com.touchtechnologies.startdagger.di.component;

import com.touchtechnologies.startdagger.di.scope.ActivityScope;
import com.touchtechnologies.startdagger.views.activity.MainActivity;

import dagger.Component;

/**
 * Created by dev-touch on 4/10/2017.
 */

@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent {

    void inject(MainActivity mainActivity);

}
