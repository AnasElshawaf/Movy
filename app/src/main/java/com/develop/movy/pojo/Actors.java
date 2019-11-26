package com.develop.movy.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Actors implements Parcelable {
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("known_for_department")
    @Expose
    private String knownForDepartment;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("known_for")
    @Expose
    private List<ActorFilms> actorFilmsList = null;

    public Actors() {
    }

    protected Actors(Parcel in) {
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readFloat();
        }
        knownForDepartment = in.readString();
        if (in.readByte() == 0) {
            gender = null;
        } else {
            gender = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        profilePath = in.readString();
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        name = in.readString();
        actorFilmsList = in.createTypedArrayList(ActorFilms.CREATOR);
    }

    public static final Creator<Actors> CREATOR = new Creator<Actors>() {
        @Override
        public Actors createFromParcel(Parcel in) {
            return new Actors(in);
        }

        @Override
        public Actors[] newArray(int size) {
            return new Actors[size];
        }
    };

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActorFilms> getActorFilmsList() {
        return actorFilmsList;
    }

    public void setActorFilmsList(List<ActorFilms> actorFilmsList) {
        this.actorFilmsList = actorFilmsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(popularity);
        }
        parcel.writeString(knownForDepartment);
        if (gender == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(gender);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(profilePath);
        parcel.writeByte((byte) (adult == null ? 0 : adult ? 1 : 2));
        parcel.writeString(name);
        parcel.writeTypedList(actorFilmsList);
    }
}