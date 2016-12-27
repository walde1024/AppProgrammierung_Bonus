package bonus.de.hska.klausurbonus.model.domain;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Walde on 26.11.16.
 */
public class Offer implements Parcelable {

    public static final String ID_KEY = "offerId";

    private String title;

    private String category;

    private String time;

    private String room;

    private String teacher;

    public Offer() {

    }

    public Offer(String title, String category, String time, String room, String teacher) {
        this.title = title;
        this.category = category;
        this.time = time;
        this.room = room;
        this.teacher = teacher;
    }

    protected Offer(Parcel in) {
        title = in.readString();
        category = in.readString();
        time = in.readString();
        room = in.readString();
        teacher = in.readString();
    }

    public static final Creator<Offer> CREATOR = new Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(category);
        parcel.writeString(time);
        parcel.writeString(room);
        parcel.writeString(teacher);
    }

}
