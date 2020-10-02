package com.example.todue;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskInfo implements Parcelable {
    private String mTitle;
    private String mDescription;
    private boolean mIsComplete;

    public TaskInfo()
    {
        this("", "", false);
    }

    public TaskInfo(String title, String description)
    {
        this(title, description, false);
    }

    public TaskInfo(String title, String description, boolean isComplete)
    {
        this.mTitle = title;
        this.mDescription = description;
        this.mIsComplete = isComplete;
    }

    private TaskInfo(Parcel parcel) {
        mTitle = parcel.readString();
        mDescription = parcel.readString();
        mIsComplete = parcel.readByte() != 0; // mIsComplete == true if byte != 0
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public void setComplete(boolean complete) {
        mIsComplete = complete;
    }

    private String getCompareKey() {
        return mTitle + "|" + mDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskInfo that = (TaskInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeByte((byte) (mIsComplete ? 1 : 0)); //if mIsComplete == true, byte == 1
    }

    public static final Parcelable.Creator<TaskInfo> CREATOR =
            new Parcelable.Creator<TaskInfo>() {
                @Override
                public TaskInfo createFromParcel(Parcel parcel) {
                    return new TaskInfo(parcel);
                }

                @Override
                public TaskInfo[] newArray(int size) {
                    return new TaskInfo[size];
                }
            };
}
