package pl.wroclaw.asi.sesja_linuksowa.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pl.wroclaw.asi.sesja_linuksowa.Database.DataBaseHelper;
import pl.wroclaw.asi.sesja_linuksowa.Lecture.LecturesExpandableAdapter;
import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavoritesActivity extends AppCompatActivity {
        @Bind(R.id.tool_bar)                Toolbar mToolbar;
        @Bind(R.id.lecture_recycler_favs)   RecyclerView mFavRecycler;
        private List<Lecture>               lectureList;
        DataBaseHelper DBHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_favorites);
            ButterKnife.bind(this);

            DBHelper = new DataBaseHelper(getApplicationContext());
            lectureList = DBHelper.getLecturesArray();


            mFavRecycler.setAdapter(new LecturesExpandableAdapter(getApplicationContext(), cutFav(lectureList), true));
            mFavRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mFavRecycler.setHasFixedSize(true);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    startActivity(about);
                    return true;
                case R.id.action_contact:
                    Intent contact = new Intent(this, ContactActivity.class);
                    contact.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(contact);
                    return true;
                case R.id.action_favorites:
                    mFavRecycler.setAdapter(new LecturesExpandableAdapter(getApplicationContext(), cutFav(lectureList), true));
                    mFavRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    mFavRecycler.setHasFixedSize(true);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

    public List<Lecture> cutFav(List<Lecture> lectures) {
        List<Lecture> toReturn = new ArrayList<>();
        for (Lecture it : lectures) {
            if (it.isFavourite())
                toReturn.add(it);
        }return toReturn;
    }
}

