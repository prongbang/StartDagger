package com.touchtechnologies.startdagger.data.db.storage;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by dev-touch on 2/16/2017.
 */

public interface AbstractStorage<T extends RealmModel, PK> {

    void add(T obj);

    void update(T obj);

    T getById(PK id);

    RealmResults<T> getAll();

    T getFirst();

    T getLast();

    void clearAll();

    void clearById(PK id);

}
