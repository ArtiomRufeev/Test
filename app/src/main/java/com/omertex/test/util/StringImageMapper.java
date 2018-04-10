package com.omertex.test.util;

import com.omertex.test.model.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class StringImageMapper {
    public static ArrayList<Data> mapStringsAndImages(ArrayList<Data> strings, ArrayList<Data> images) {
        HashMap<String, Data> imageDataHashMap = new HashMap<>();
        for (Data data : images) {
            imageDataHashMap.put(data.getId(), data);
        }
        for (Data data : strings) {
            if (imageDataHashMap.containsKey(data.getId())) {
                data.setImageUrl(imageDataHashMap.get(data.getId()).getImageUrl());
            }
        }
        return strings;
    }
}
