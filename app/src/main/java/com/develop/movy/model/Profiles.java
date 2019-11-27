package com.develop.movy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profiles {
    @SerializedName("iso_639_1")
    @Expose
    public Object iso6391;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
    @SerializedName("vote_average")
    @Expose
    public Float voteAverage;
    @SerializedName("file_path")
    @Expose
    public String filePath;
    @SerializedName("aspect_ratio")
    @Expose
    public Float aspectRatio;

    public Profiles() {
    }

    public Object getIso6391() {
        return iso6391;
    }

    public void setIso6391(Object iso6391) {
        this.iso6391 = iso6391;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
}
