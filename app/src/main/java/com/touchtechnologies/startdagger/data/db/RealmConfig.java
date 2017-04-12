package com.touchtechnologies.startdagger.data.db;

import io.realm.RealmConfiguration;

/**
 * Created by dev-touch on 4/3/2017.
 */

public class RealmConfig {

    public static RealmConfiguration init() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("start-dagger.realm")
                .schemaVersion(42)
                .deleteRealmIfMigrationNeeded()
                .build();

        return config;
    }

}
