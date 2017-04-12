package com.touchtechnologies.startdagger.data.db.storage;

import com.touchtechnologies.startdagger.data.db.model.UserModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by dev-touch on 2/16/2017.
 */

public class UserStorage implements AbstractStorage<UserModel, String> {

    private static final String TAG = UserStorage.class.getSimpleName();

    @Override
    public void add(UserModel obj) {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(obj);
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public void update(UserModel obj) {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(obj);
            realm.commitTransaction();
            realm.close();
        }
    }

    @Override
    public UserModel getById(String id) {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            RealmResults<UserModel> realmResults = realm.where(UserModel.class).equalTo("userId", id).findAll();
            if (realmResults.size() > 0) {
                return realmResults.first();
            }
        }
        return null;
    }

    @Override
    public RealmResults<UserModel> getAll() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            return realm.where(UserModel.class).findAll();
        }
        return null;
    }

    @Override
    public UserModel getFirst() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            RealmResults<UserModel> realmResults = getAll();
            if (realmResults.size() > 0)
                return realmResults.first();
        }
        return null;
    }

    @Override
    public UserModel getLast() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            RealmResults<UserModel> realmResults = getAll();
            if (realmResults.size() > 0)
                return realmResults.last();
        }
        return null;
    }

    @Override
    public void clearAll() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            if (realm.where(UserModel.class).count() != 0) {
                realm.beginTransaction();
                realm.delete(UserModel.class);
                realm.commitTransaction();
                realm.close();
            }
        }
    }

    @Override
    public void clearById(String id) {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            final RealmResults<UserModel> results = realm.where(UserModel.class).equalTo("userId", id).findAll();
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
