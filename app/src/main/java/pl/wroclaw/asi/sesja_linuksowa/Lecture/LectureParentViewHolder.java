package pl.wroclaw.asi.sesja_linuksowa.Lecture;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LectureParentViewHolder extends ParentViewHolder {
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.lecture_list_parent_arrow) ImageView mArrow;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.lecture_list_parent_time) TextView mTime;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.lecture_list_parent_time_end) TextView mTimeEnd;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.lecture_list_parent_title) TextView mTitle;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.lecture_card_view) CardView mParentCard;
    private Context context;
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    public RecyclerView rv;

    public LectureParentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        rv = null;
    }

    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);

        if (expanded) {
            mParentCard.setCardElevation(4 * context.getResources().getDisplayMetrics().density);
            Animation rotate = AnimationUtils.loadAnimation(context, pl.wroclaw.asi.sesja_linuksowa.R.anim.rotate_picture_down);
            mArrow.startAnimation(rotate);
            mArrow.setRotation(ROTATED_POSITION);
            if (rv != null){
                rv.scrollTo(0, rv.getLayoutManager().getHeight());
            }

        } else {
            mParentCard.setCardElevation(3 * context.getResources().getDisplayMetrics().density);
            Animation rotate = AnimationUtils.loadAnimation(context, pl.wroclaw.asi.sesja_linuksowa.R.anim.rotate_picture);
            mArrow.startAnimation(rotate);
            mArrow.setRotation(INITIAL_POSITION);
        }
    }
}
