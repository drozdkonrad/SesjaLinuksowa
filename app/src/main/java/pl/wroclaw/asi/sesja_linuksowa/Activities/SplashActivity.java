package pl.wroclaw.asi.sesja_linuksowa.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import pl.wroclaw.asi.sesja_linuksowa.MainActivity;
import pl.wroclaw.asi.sesja_linuksowa.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SplashActivity extends Activity implements LoadingTask.LoadingTaskFinishedListener {
    @Bind(R.id.progress_bar) ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        new LoadingTask(progressBar,getApplicationContext(),this).execute();
    }

    @Override
    public void onTaskFinished() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}