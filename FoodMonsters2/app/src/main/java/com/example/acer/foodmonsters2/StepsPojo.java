package com.example.acer.foodmonsters2;

import android.os.Parcel;
import android.os.Parcelable;

public class StepsPojo implements Parcelable {
    public static final Creator<StepsPojo> CREATOR = new Creator<StepsPojo>() {
        @Override
        public StepsPojo createFromParcel(Parcel in) {
            return new StepsPojo(in);
        }

        @Override
        public StepsPojo[] newArray(int size) {
            return new StepsPojo[size];
        }
    };
    int id;
    String shortdescription, description, videourl, thumbnailurl;

    public StepsPojo(int fid, String fshortdescription, String fdescription, String fvideourl, String fthumbnailurl) {
        this.id = fid;
        this.shortdescription = fshortdescription;
        this.description = fdescription;
        this.videourl = fvideourl;
        this.thumbnailurl = fthumbnailurl;
    }

    protected StepsPojo(Parcel in) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getShortdescription());
        dest.writeString(getDescription());
        dest.writeString(getVideourl());
        dest.writeString(getThumbnailurl());
    }
}
