package pl.wroclaw.asi.sesja_linuksowa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.wroclaw.asi.sesja_linuksowa.Activities.AboutActivity;
import pl.wroclaw.asi.sesja_linuksowa.Activities.ContactActivity;
import pl.wroclaw.asi.sesja_linuksowa.Activities.FavoritesActivity;
import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.Model.Speaker;
import pl.wroclaw.asi.sesja_linuksowa.Speaker.SpeakersExpandableAdapter;

public class MainActivity extends AppCompatActivity implements SpeakersExpandableAdapter.LectureClicked {

    @Bind(R.id.tool_bar)        Toolbar mToolbar;
    @Bind(R.id.tabs)            TabLayout mTabLayout;
    @Bind(R.id.viewpager)       ViewPager mViewPager;
    private ViewPagerAdapter    mViewPagerAdapter;

    ArrayList<Lecture> lectureList;
    ArrayList<Speaker> speakerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mViewPagerAdapter);
        setSupportActionBar(mToolbar);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        exampleTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent about = new Intent(this, AboutActivity.class);
                about.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(about);                return true;
            case R.id.action_contact:
                Intent contact = new Intent(this, ContactActivity.class);
                contact.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(contact);
                return true;
            case R.id.action_favorites:
                Intent fav = new Intent(this, FavoritesActivity.class);
                fav.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(fav);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void exampleTabs() {
        final TabLayout.Tab day1 = mTabLayout.newTab();
        final TabLayout.Tab day2 = mTabLayout.newTab();
        final TabLayout.Tab speakers = mTabLayout.newTab();
        final TabLayout.Tab collaborators = mTabLayout.newTab();
        final TabLayout.Tab party = mTabLayout.newTab();

        day1.setText("Dzień I");
        day2.setText("Dzień II");
        speakers.setText("Prelegenci");
        collaborators.setText("Partnerzy");
        party.setText("Middle Party");

        mTabLayout.addTab(day1, 0);
        mTabLayout.addTab(day2, 1);
        mTabLayout.addTab(speakers, 2);
        mTabLayout.addTab(collaborators, 3);
        mTabLayout.addTab(party, 4);

        mTabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));


        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                tab.select();
            }
        });
    }

    @Override
    public void onLectureClicked(Lecture lecture) {
        if (mTabLayout != null) {
            if (lecture.isFirstDay() && mTabLayout.getTabAt(0) != null) {
                mTabLayout.getTabAt(0).select();
            } else if (lecture.isSecondDay() && mTabLayout.getTabAt(1) != null) {
                mTabLayout.getTabAt(1).select();
            }
        }
    }

//    @Override
//    public void onSpeakerClicked(int id) {
//        SpeakersFragment fragment = new SpeakersFragment();
//        Bundle args = new Bundle();
//        args.putInt("id", id);
//        fragment.setArguments(args);
//        mViewPagerAdapter.getItem(2);
//    }
}
