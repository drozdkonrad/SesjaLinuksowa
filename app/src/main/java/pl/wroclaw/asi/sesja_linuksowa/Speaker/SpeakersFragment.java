package pl.wroclaw.asi.sesja_linuksowa.Speaker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.wroclaw.asi.sesja_linuksowa.Database.DataBaseHelper;
import pl.wroclaw.asi.sesja_linuksowa.Model.Speaker;
import pl.wroclaw.asi.sesja_linuksowa.SessionInfoService;


public class SpeakersFragment extends Fragment {
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.speaker_recycler) RecyclerView mRecyclerView;

    private List<Speaker> speakerList;
    private SessionInfoService sessionInfoService;
    DataBaseHelper DBHelper;

    SpeakersExpandableAdapter.LectureClicked mLectureClicked;

    public static Fragment newInstance(SpeakersExpandableAdapter.LectureClicked lectureClicked) {
        SpeakersFragment f = new SpeakersFragment();
        f.mLectureClicked = lectureClicked;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(pl.wroclaw.asi.sesja_linuksowa.R.layout.speaker_list, container, false);
        ButterKnife.bind(this, view);
        DBHelper = new DataBaseHelper(getActivity().getApplicationContext());
        sessionInfoService = new SessionInfoService();
        speakerList = DBHelper.getSpeakersArray();

        mRecyclerView.setAdapter(new SpeakersExpandableAdapter(getActivity().getApplicationContext(), speakerList, mLectureClicked));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);

        return view;
    }
}
