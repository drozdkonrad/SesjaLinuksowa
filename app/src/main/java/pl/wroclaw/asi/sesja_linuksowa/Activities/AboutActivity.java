package pl.wroclaw.asi.sesja_linuksowa.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import pl.wroclaw.asi.sesja_linuksowa.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {
    @Bind(R.id.tool_bar)                Toolbar mToolbar;
    @Bind(R.id.previous_session_button) ImageButton mPreviousSessionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick (R.id.previous_session_button)
    public void watchYT(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=eerTOTRAF1w&list=PLTvZBPQxCI8FlBKzCESV2tIq7XF50_LgE&index=1")));
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
//                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
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
}


