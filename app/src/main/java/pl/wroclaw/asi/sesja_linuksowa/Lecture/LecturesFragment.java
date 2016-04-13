package pl.wroclaw.asi.sesja_linuksowa.Lecture;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.wroclaw.asi.sesja_linuksowa.Database.DataBaseHelper;
import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LecturesFragment extends Fragment {
    @Bind(R.id.lecture_recycler) RecyclerView mRecyclerView;

    private List<Lecture> lectureList;
    DataBaseHelper DBHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lecture_list, container, false);
        ButterKnife.bind(this, view);
        DBHelper = new DataBaseHelper(getActivity().getApplicationContext());
        lectureList = DBHelper.getLecturesArray();

        mRecyclerView.setAdapter(new LecturesExpandableAdapter(getActivity().getApplicationContext(), cutDay(lectureList), false));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        return view;
    }

    public List<Lecture> cutDay(List<Lecture> lectures) {
        List<Lecture> toReturn = new ArrayList<>();
        if(getArguments().getInt("day") == 1)
            for(Lecture it:lectures) {
                if(it.isFirstDay())
                    toReturn.add(it);
            }
        else if (getArguments().getInt("day") == 2)
            for(Lecture it:lectures) {
                if(it.isSecondDay())
                    toReturn.add(it);
            }
        return toReturn;
    }
}