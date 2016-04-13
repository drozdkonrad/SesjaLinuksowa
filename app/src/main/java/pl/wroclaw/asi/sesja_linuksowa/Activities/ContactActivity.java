package pl.wroclaw.asi.sesja_linuksowa.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactActivity extends AppCompatActivity {
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.tool_bar)          Toolbar mToolbar;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.czerpak_mail)      TextView czerpakMail;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.plonka_mail)       TextView plonkaMail;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.przybylek_mail)    TextView przybylekMail;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.drozd_mail)        TextView drozdMail;
    @Bind(pl.wroclaw.asi.sesja_linuksowa.R.id.jurec_mail)        TextView jurecMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(pl.wroclaw.asi.sesja_linuksowa.R.layout.activity_contact);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(pl.wroclaw.asi.sesja_linuksowa.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case pl.wroclaw.asi.sesja_linuksowa.R.id.action_about:
                Intent about = new Intent(this, AboutActivity.class);
                about.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(about);
                return true;
            case pl.wroclaw.asi.sesja_linuksowa.R.id.action_contact:
//                Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();
                return true;
            case pl.wroclaw.asi.sesja_linuksowa.R.id.action_favorites:
                Intent fav = new Intent(this, FavoritesActivity.class);
                fav.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(fav);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({pl.wroclaw.asi.sesja_linuksowa.R.id.czerpak_mail, pl.wroclaw.asi.sesja_linuksowa.R.id.przybylek_mail, pl.wroclaw.asi.sesja_linuksowa.R.id.plonka_mail, pl.wroclaw.asi.sesja_linuksowa.R.id.drozd_mail, pl.wroclaw.asi.sesja_linuksowa.R.id.jurec_mail})
    public void sendMail(TextView receiver){
        String address = receiver.getText().toString();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{address});
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}