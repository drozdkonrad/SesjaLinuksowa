package pl.wroclaw.asi.sesja_linuksowa.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;
@DatabaseTable(tableName = "lectures")
public class Lecture implements ParentListItem {

    @DatabaseField(columnName = "lecture_name",id=true) @SerializedName("name")         private String mName;
    @DatabaseField(columnName = "lecture_description")  @SerializedName("description")  private String mDescription;
    @DatabaseField(columnName = "lecture_starttime")    @SerializedName("start_time")   private String mStartTime;
    @DatabaseField(columnName = "lecture_endtime")      @SerializedName("end_time")     private String mEndTime;
    @DatabaseField(columnName = "lecture_language")     @SerializedName("language")     private String mLanguage;
    @DatabaseField(columnName = "lecture_roomid")       @SerializedName("room_id")      private int mRoomId;
    @DatabaseField(columnName = "lecture_day")          @SerializedName("day")          private long day;
    @DatabaseField(columnName = "lecture_speakerid")    @SerializedName("speaker_id")   private int mSpeakerId;
    @DatabaseField(columnName = "is_favourite")                                         private boolean isFavourite;


    private ArrayList<Lecture> mChildrenList;

    public Lecture() {
    }

    public Lecture(String mName, String mDescription, String mStartTime, String mEndTime, int mSpeakerId,
                   int mRoomId, long day, boolean isFavourite) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mStartTime = mStartTime;
        this.mEndTime = mEndTime;
        this.mSpeakerId = mSpeakerId;
        this.mRoomId = mRoomId;
        this.day = day;
        this.isFavourite = isFavourite;
        mChildrenList = new ArrayList<>();
        mChildrenList.add(this);
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public boolean isFirstDay(){
        return (day == 1459584000000L);
    }
    public boolean isSecondDay() {
        return (day == 1459670400000L);
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String mLanguage) {
        this.mLanguage = mLanguage;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public int getSpeakerId() {
        return mSpeakerId;
    }

    public void setSpeakerId(int mSpeakerId) {
        this.mSpeakerId = mSpeakerId;
    }

    public int getRoomId() {
        return mRoomId;
    }

    public void setRoomId(int mRoomId) {
        this.mRoomId = mRoomId;
    }


    @Override
    public List<?> getChildItemList() {
        mChildrenList = new ArrayList<>();
        mChildrenList.add(this);
        return mChildrenList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}