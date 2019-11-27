package com.develop.movy.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActorResource {
    @SerializedName("id")
    public Integer id;
    @SerializedName("profiles")
    public List<Profiles> profiles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profiles> profiles) {
        this.profiles = profiles;
    }

    public ActorResource() {
    }
}
