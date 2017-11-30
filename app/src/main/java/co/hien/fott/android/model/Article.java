package co.hien.fott.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import co.hien.fott.android.helper.Utils;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class Article extends BaseModel implements Parcelable{
    private long id;
    private String title;
    private String dateTime;
    private ArrayList<String> tags;
    private ArrayList<Item> content;
    private String ingress;
    private String image;
    private long created;
    private long changed;

    public Article(){

    }

    protected Article(Parcel in) {
        id = in.readLong();
        title = in.readString();
        dateTime = in.readString();
        tags = in.createStringArrayList();
        content = in.createTypedArrayList(Item.CREATOR);
        ingress = in.readString();
        image = in.readString();
        created = in.readLong();
        changed = in.readLong();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return Utils.getFormatTime(dateTime);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<Item> getContent() {
        return content;
    }

    public void setContent(ArrayList<Item> content) {
        this.content = content;
    }

    public String getIngress() {
        return ingress;
    }

    public void setIngress(String ingress) {
        this.ingress = ingress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getChanged() {
        return changed;
    }

    public void setChanged(long changed) {
        this.changed = changed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(dateTime);
        parcel.writeStringList(tags);
        parcel.writeTypedList(content);
        parcel.writeString(ingress);
        parcel.writeString(image);
        parcel.writeLong(created);
        parcel.writeLong(changed);
    }
}
