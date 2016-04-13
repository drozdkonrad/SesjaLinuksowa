package pl.wroclaw.asi.sesja_linuksowa.Lecture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bumptech.glide.Glide;
import pl.wroclaw.asi.sesja_linuksowa.Database.DataBaseHelper;
import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.Model.Speaker;
import pl.wroclaw.asi.sesja_linuksowa.R;

import java.sql.SQLException;
import java.util.List;

import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class LecturesExpandableAdapter extends ExpandableRecyclerAdapter<LectureParentViewHolder,LectureChildViewHolder> {
    LayoutInflater mInflater;
    Context context;
    boolean isFav;
    public LecturesExpandableAdapter(Context context, List<? extends ParentListItem> parentItemList, boolean isFav) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.isFav = isFav;
    }

    @Override
    public LectureParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view;
        if (isFav) {
            view = mInflater.inflate(R.layout.list_item_lecture_parent_fav, parentViewGroup, false);
        } else{
            view = mInflater.inflate(R.layout.list_item_lecture_parent, parentViewGroup, false);
        }
        return new LectureParentViewHolder(view);
    }

    @Override
    public LectureChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {

        View view = mInflater.inflate(R.layout.list_item_lecture_child, childViewGroup, false);
        return new LectureChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(LectureParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Lecture lecture = (Lecture) parentListItem;
        parentViewHolder.mTitle.setText(lecture.getName());
        String start = lecture.getStartTime();
        parentViewHolder.mTime.setText(start);
        if (isFav)
            parentViewHolder.mTimeEnd.setText(((Lecture) parentListItem).isFirstDay() ? "Dzień I" : "Dzień II");
        else {
            parentViewHolder.mTimeEnd.setText("" + start.charAt(0) + (Integer.parseInt(String.valueOf(start.charAt(1)))+1) +start.substring(2));
        }

    }

    @Override
    public void onBindChildViewHolder(LectureChildViewHolder childViewHolder, int position, Object childListItem) {
        final DataBaseHelper helper = new DataBaseHelper(context);
        final Lecture lectureChild = (Lecture) childListItem;

        Speaker speaker = helper.getSpeaker(lectureChild.getSpeakerId());
        childViewHolder.mDescription.setText(lectureChild.getDescription());

        if(lectureChild.isFavourite()) {
            childViewHolder.mFavoriteButton.setChecked(true);
            childViewHolder.mFavoriteButton.setActivated(true);
        }
        else {
            childViewHolder.mFavoriteButton.setChecked(false);
            childViewHolder.mFavoriteButton.setActivated(false);
        }

        childViewHolder.mSpeakerFirstName.setText(speaker.getFirstName());
        childViewHolder.mSpeakerLastName.setText(speaker.getLastName());
        childViewHolder.mSpeakerFirstName.setText(speaker.getFirstName());
        if (!speaker.getNickname().equals(""))
            childViewHolder.mSpeakerNickName.setText("."+speaker.getNickname());
        else
            childViewHolder.mSpeakerNickName.setText("");

        Glide.with(context)
                .load(speaker.getImageUrl())
                .bitmapTransform(new CropCircleTransformation(context))
                .placeholder(R.drawable.speaker_placeholder)
                .into(childViewHolder.mSpeakerImage);

        childViewHolder.mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteLecture(lectureChild);
                if (lectureChild.isFavourite()) lectureChild.setIsFavourite(false);
                else lectureChild.setIsFavourite(true);
                helper.addLecture(lectureChild);
                Toast.makeText(context, lectureChild.isFavourite() ? "Prelekcja dodana do ulubionych": "Prelekcja usunięta z ulubionych", Toast.LENGTH_LONG).show();
            }
        });
    }
}
