package com.example.testview;

import android.os.Parcel;
import android.os.Parcelable;

public class ARLayoutModel implements Parcelable {

    private String layoutName;
    private String layoutDescription;
    private String layoutImage;

    public ARLayoutModel(){

    }

    public ARLayoutModel(Parcel in){
        layoutName = in.readString();
        layoutDescription = in.readString();
        layoutImage = in.readString();
    }

    public static final Creator<ARLayoutModel> CREATOR = new Creator<ARLayoutModel>() {
        @Override
        public ARLayoutModel createFromParcel(Parcel in) {
            return new ARLayoutModel(in);
        }

        @Override
        public ARLayoutModel[] newArray(int size) {
            return new ARLayoutModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(layoutName);
        dest.writeString(layoutDescription);
        dest.writeString(layoutImage);
    }

    public int getLayoutImageId() {
        return layoutImageId;
    }

    public void setLayoutImageId(int layoutImageId) {
        this.layoutImageId = layoutImageId;
    }

    private int layoutImageId;

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public String getLayoutDescription() {
        return layoutDescription;
    }

    public void setLayoutDescription(String layoutDescription) {
        this.layoutDescription = layoutDescription;
    }

    public String getLayoutImage() {
        return layoutImage;
    }

    public void setLayoutImage(String layoutImage) {
        this.layoutImage = layoutImage;
    }
}
