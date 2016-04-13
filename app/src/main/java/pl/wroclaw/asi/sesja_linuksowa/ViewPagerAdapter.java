package pl.wroclaw.asi.sesja_linuksowa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import pl.wroclaw.asi.sesja_linuksowa.Lecture.LecturesFragment;
import pl.wroclaw.asi.sesja_linuksowa.Partner.PartnersFragment;
import pl.wroclaw.asi.sesja_linuksowa.Speaker.SpeakersExpandableAdapter;
import pl.wroclaw.asi.sesja_linuksowa.Speaker.SpeakersFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    SpeakersExpandableAdapter.LectureClicked mLectureClicked;

    public ViewPagerAdapter(FragmentManager fm, SpeakersExpandableAdapter.LectureClicked lectureClicked) {
        super(fm);
        mLectureClicked = lectureClicked;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LecturesFragment firstDay = new LecturesFragment();
                Bundle firstArgs = new Bundle();
                firstArgs.putInt("day", 1);
                firstDay.setArguments(firstArgs);
                return firstDay;
            case 1:
                LecturesFragment secondDay = new LecturesFragment();
                Bundle secondArgs = new Bundle();
                secondArgs.putInt("day", 2);
                secondDay.setArguments(secondArgs);
                return secondDay;
            case 2:
                return SpeakersFragment.newInstance(mLectureClicked);
            case 3:
                return new PartnersFragment();
            case 4:
                return new MiddlePartyFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}