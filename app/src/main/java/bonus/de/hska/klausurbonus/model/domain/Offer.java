package bonus.de.hska.klausurbonus.model.domain;

import android.graphics.drawable.Drawable;

/**
 * Created by Walde on 26.11.16.
 */
public class Offer {

    private Drawable icon;

    private String title;

    private String category;

    private String time;

    private String room;

    private String teacher;

    public Offer() {

    }

    public Offer(Drawable icon, String title, String category, String time, String room, String teacher) {
        this.icon = icon;
        this.title = title;
        this.category = category;
        this.time = time;
        this.room = room;
        this.teacher = teacher;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

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
}
