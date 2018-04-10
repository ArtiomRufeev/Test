package com.omertex.test.db;


import android.content.Context;

import com.omertex.test.model.Data;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DBHelper {
    private static final String FILENAME = "data.realm";

    public static void initRealm(Context context){
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name(FILENAME).build();
        Realm.setDefaultConfiguration(config);
    }

    public static void saveDataToRealm(final ArrayList<Data> data) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
                realm.delete(DataRealm.class);
                realm.insert(DataMapper.map(data));
            }
        });
    }

    public static ArrayList<Data> retrieveDataFromRealm() {
        RealmResults<DataRealm> realmResults = Realm.getDefaultInstance().where(DataRealm.class).findAll();
        return DataMapper.map(realmResults);
    }
}
