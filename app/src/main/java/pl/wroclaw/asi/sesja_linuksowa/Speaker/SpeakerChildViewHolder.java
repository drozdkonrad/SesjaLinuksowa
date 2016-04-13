package pl.wroclaw.asi.sesja_linuksowa.Speaker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import pl.wroclaw.asi.sesja_linuksowa.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SpeakerChildViewHolder extends ChildViewHolder {
    @Bind(R.id.speaker_list_child_description)  TextView mDescription;
    @Bind(R.id.list_item_speaker_recycler)      RecyclerView mLectureListRec;

    public SpeakerChildViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
