package com.touchtechnologies.startdagger.data.db.storage;

import com.touchtechnologies.startdagger.data.db.model.AccessTokenModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class AccessTokenStorage implements AbstractStorage<AccessTokenModel, String> {

    private static final String TAG = AccessTokenStorage.class.getSimpleName();

    @Override
    public void add(AccessTokenModel obj) {
        clearAll();
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(obj);
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public void update(AccessTokenModel obj) {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(obj);
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public AccessTokenModel getById(String id) {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            RealmResults<AccessTokenModel> realmResults = realm.where(AccessTokenModel.class).equalTo("userId", id).findAll();
            if (realmResults.size() > 0) {
                return realmResults.first();
            }
        }
        return null;
    }

    @Override
    public RealmResults<AccessTokenModel> getAll() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            return realm.where(AccessTokenModel.class).findAll();
        }
        return null;
    }

    @Override
    public AccessTokenModel getFirst() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            RealmResults<AccessTokenModel> realmResults = getAll();
            if (realmResults.size() > 0)
                return realmResults.first();
        }
        return null;
    }

    @Override
    public AccessTokenModel getLast() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            RealmResults<AccessTokenModel> realmResults = getAll();
            if (realmResults.size() > 0)
                return realmResults.last();
        }
        return null;
    }

    @Override
    public void clearAll() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            if (realm.where(AccessTokenModel.class).count() != 0) {
                realm.beginTransaction();
                realm.delete(AccessTokenModel.class);
                realm.commitTransaction();
                realm.close();
            }
        }
    }

    @Override
    public void clearById(String id) {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            final RealmResults<AccessTokenModel> results = realm.where(AccessTokenModel.class).equalTo("userId", id).findAll();
            // All changes to data must happen in a transaction
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (results.size() > 0) {
                        // Delete all matches
                        results.deleteAllFromRealm();
                    }
                }
            });
        }
    }
}
