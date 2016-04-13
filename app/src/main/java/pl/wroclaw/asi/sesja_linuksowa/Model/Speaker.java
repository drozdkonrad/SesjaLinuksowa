package pl.wroclaw.asi.sesja_linuksowa.Model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@DatabaseTable(tableName = "speakers")
public class Speaker implements ParentListItem, Serializable {
    @DatabaseField(columnName = "speaker_id", id = true)           @SerializedName("speaker_id")   private int mId;
    @DatabaseField(columnName = "speaker_firstname")    @SerializedName("first_name")   private String mFirstName;
    @DatabaseField(columnName = "speaker_nickname")     @SerializedName("nickname")     private String mNickname;
    @DatabaseField(columnName = "speaker_lastname")     @SerializedName("last_name")    private String mLastName;
    @DatabaseField(columnName = "speaker_description")  @SerializedName("description")  private String mDescription;
    @DatabaseField(columnName = "speaker_imagepath")    @SerializedName("image_path")   private String mImageUrl;


    private ArrayList<Speaker> mChildrenList;

    public Speaker() {
    }

    public Speaker(String mFirstName, String mNickname, String mLastName, String mDescription, String mImageUrl) {
        this.mFirstName = mFirstName;
        this.mNickname = mNickname;
        this.mLastName = mLastName;
        this.mDescription = mDescription;
        this.mImageUrl = mImageUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getNickname() {
        return mNickname;
    }

    public void setNickname(String mNickname) {
        this.mNickname = mNickname;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
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