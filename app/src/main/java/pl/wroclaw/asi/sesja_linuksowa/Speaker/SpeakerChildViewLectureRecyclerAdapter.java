package pl.wroclaw.asi.sesja_linuksowa.Speaker;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.wroclaw.asi.sesja_linuksowa.Database.DataBaseHelper;
import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;

public class SpeakerChildViewLectureRecyclerAdapter extends RecyclerView.Adapter<SpeakerChildViewLectureRecyclerAdapter.ViewHolder> {
    Context context;
    int speakerId;
    SpeakersExpandableAdapter.LectureClicked mLectureClicked;

    public SpeakerChildViewLectureRecyclerAdapter(Context context, int speakerId, SpeakersExpandableAdapter.LectureClicked lectureClicked) {
        this.context = context;
        this.speakerId = speakerId;
        mLectureClicked = lectureClicked;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(pl.wroclaw.asi.sesja_linuksowa.R.layout.list_item_speaker_child_lecture_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        for (Lecture l : new DataBaseHelper(context).getLecturesArray())
            if (l.getSpeakerId()==speakerId) {
                holder.setLecture(l);
            }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.speaker_list_child_lecture_title) TextView mLectureTitle;
        @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.speaker_list_child_lecture_time) TextView mLectureTime;
        @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.speaker_list_child_lecture_day) TextView mLectureDay;
        @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.speaker_lecture_card_view)
        CardView mParent;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setLecture(final Lecture lecture) {
            mLectureTitle.setText(lecture.getName());
            mLectureDay.setText((lecture.isFirstDay()) ? "Dzień I" : "Dzień II");
            mLectureTime.setText(lecture.getStartTime());


            mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLectureClicked.onLectureClicked(lecture);
                }
            });
        }
    }
}


