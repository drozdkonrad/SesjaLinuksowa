package pl.wroclaw.asi.sesja_linuksowa.Speaker;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.Model.Speaker;
import pl.wroclaw.asi.sesja_linuksowa.R;


public class SpeakersExpandableAdapter extends ExpandableRecyclerAdapter<SpeakerParentViewHolder,SpeakerChildViewHolder> {


    LayoutInflater mInflater;
    Context context;
    LectureClicked mLectureClicked;

    public SpeakersExpandableAdapter(Context context, List<? extends ParentListItem> parentItemList, LectureClicked lectureClicked) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
        this.context = context;
        mLectureClicked = lectureClicked;
    }

    @Override
    public SpeakerParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mInflater.inflate(pl.wroclaw.asi.sesja_linuksowa.R.layout.list_item_speaker_parent, parentViewGroup, false);
        return new SpeakerParentViewHolder(view);
    }

    @Override
    public SpeakerChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mInflater.inflate(pl.wroclaw.asi.sesja_linuksowa.R.layout.list_item_speaker_child, childViewGroup, false);
        return new SpeakerChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(SpeakerParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Speaker speaker = (Speaker) parentListItem;
        parentViewHolder.mFirstName.setText(speaker.getFirstName());
        if (!speaker.getNickname().equals(""))
            parentViewHolder.mNickName.setText("."+speaker.getNickname());
        else
            parentViewHolder.mNickName.setText("");
        parentViewHolder.mLastName.setText(speaker.getLastName());

        Glide.with(context)
                .load(speaker.getImageUrl())
                .override(350, 350)
                .placeholder(R.drawable.speaker_placeholder)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(parentViewHolder.mSpeakerImage);
    }

    @Override
    public void onBindChildViewHolder(SpeakerChildViewHolder childViewHolder, int position, Object childListItem) {
        Speaker speakerChild = (Speaker) childListItem;
        childViewHolder.mDescription.setText(speakerChild.getDescription());

        childViewHolder.mLectureListRec.setAdapter(new SpeakerChildViewLectureRecyclerAdapter(context, speakerChild.getId(), mLectureClicked));
        childViewHolder.mLectureListRec.setLayoutManager(new LinearLayoutManager(context));
        childViewHolder.mLectureListRec.setHasFixedSize(true);
    }

    public interface LectureClicked {
        void onLectureClicked(Lecture lecture);
    }
}
