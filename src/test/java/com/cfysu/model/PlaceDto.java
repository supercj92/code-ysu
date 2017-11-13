package com.cfysu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlaceDto implements Serializable {

    private String id;
    private String name;

    private List<PlaceDto> subPlaceList = new ArrayList<PlaceDto>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlaceDto> getSubPlaceList() {
        return subPlaceList;
    }

    public void setSubPlaceList(List<PlaceDto> subPlaceList) {
        this.subPlaceList = subPlaceList;
    }
}
