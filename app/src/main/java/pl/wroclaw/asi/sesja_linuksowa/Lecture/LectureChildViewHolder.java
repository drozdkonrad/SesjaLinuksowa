package pl.wroclaw.asi.sesja_linuksowa.Lecture;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import pl.wroclaw.asi.sesja_linuksowa.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LectureChildViewHolder extends ChildViewHolder {

    @Bind(R.id.lecture_list_child_card_favorite_button) CheckBox mFavoriteButton;
    @Bind(R.id.lecture_list_child_image) ImageView mSpeakerImage;
    @Bind(R.id.lecture_list_child_firstName) TextView mSpeakerFirstName;
    @Bind(R.id.lecture_list_child_lastName) TextView mSpeakerLastName;
    @Bind(R.id.lecture_list_child_nickName) TextView mSpeakerNickName;
    @Bind(R.id.lecture_list_child_description) TextView mDescription;

    public LectureChildViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
