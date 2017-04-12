package com.touchtechnologies.startdagger.views.utils;

/**
 * Created by dev-touch on 4/10/2017.
 */

public interface LifecycleProvider {

    void addLifecycleListener(LifecycleListener listener);

    void removeLifecycleListener(LifecycleListener listener);

}
