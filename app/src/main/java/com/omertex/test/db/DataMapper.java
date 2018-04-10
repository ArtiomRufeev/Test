package com.omertex.test.db;


import com.omertex.test.model.Data;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmResults;

public class DataMapper {

    public static Data map(DataRealm dataRealm) {
        Data data = new Data();
        data.setId(dataRealm.getId());
        data.setName(dataRealm.getName());
        data.setImageUrl(dataRealm.getImageUrl());
        return data;
    }

    public static ArrayList<Data> map(RealmResults<DataRealm> results) {
        ArrayList<Data> items = new ArrayList<>();
        for (DataRealm dataRealm : results) {
            items.add(map(dataRealm));
        }
        return items;
    }

    public static RealmList<DataRealm> map(ArrayList<Data> items) {
        RealmList<DataRealm> results = new RealmList<>();
        for (Data data : items) {
            results.add(map(data));
        }
        return results;
    }

    public static DataRealm map(Data data) {
        DataRealm dataRealm = new DataRealm();
        dataRealm.setId(data.getId());
        dataRealm.setName(data.getName());
        dataRealm.setImageUrl(data.getImageUrl());
        return dataRealm;
    }
}
