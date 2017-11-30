package co.hien.fott.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class Item extends BaseModel implements Parcelable {
    private String type;
    private String subject;
    private String description;

    protected Item(Parcel in) {
        type = in.readString();
        subject = in.readString();
        description = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(subject);
        parcel.writeString(description);
    }
}
