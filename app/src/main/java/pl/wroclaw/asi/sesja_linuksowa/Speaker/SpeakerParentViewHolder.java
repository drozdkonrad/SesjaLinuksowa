package pl.wroclaw.asi.sesja_linuksowa.Speaker;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import pl.wroclaw.asi.sesja_linuksowa.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SpeakerParentViewHolder extends ParentViewHolder {
    @Bind(R.id.speaker_list_parent_image)   ImageView mSpeakerImage;
    @Bind(R.id.speaker_list_parent_firstName)    TextView mFirstName;
    @Bind(R.id.speaker_list_parent_nickName)    TextView mNickName;
    @Bind(R.id.speaker_list_parent_lastName)    TextView mLastName;
    @Bind(R.id.speaker_list_parent_arrow)   ImageView mArrow;
    @Bind(R.id.speaker_card_view)           CardView mParent;


    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private Context context;

    public SpeakerParentViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (expanded) {
            mParent.setCardElevation(4 * context.getResources().getDisplayMetrics().density);
            Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate_picture_down);
            mArrow.startAnimation(rotate);
            mArrow.setRotation(ROTATED_POSITION);
        } else {
            mParent.setCardElevation(3 * context.getResources().getDisplayMetrics().density);
            Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate_picture);
            mArrow.startAnimation(rotate);
            mArrow.setRotation(INITIAL_POSITION);
        }
    }
}
