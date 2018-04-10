package com.omertex.test.db;


import io.realm.RealmObject;

public class DataRealm extends RealmObject {
    private String id;
    private String imageUrl;
    private String name;

    public DataRealm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
