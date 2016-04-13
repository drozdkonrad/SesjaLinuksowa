package pl.wroclaw.asi.sesja_linuksowa.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import pl.wroclaw.asi.sesja_linuksowa.Database.DataBaseHelper;
import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.Model.Speaker;
import pl.wroclaw.asi.sesja_linuksowa.SessionInfoService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;

public class LoadingTask extends AsyncTask<Void , Integer, Integer> {
    ProgressBar progressBar;

    ArrayList<Lecture> lectureList;
    ArrayList<Speaker> speakerList;
    Context context;
    DataBaseHelper helper;
    private SessionInfoService sessionInfoService;
    private final LoadingTaskFinishedListener finishedListener;

    @Override
    protected Integer doInBackground(Void... params) {
        helper = new DataBaseHelper(context);

        if(resourcesDontAlreadyExist()){
            downloadResources();
        }else delay();
        return 1234;
    }
    public interface LoadingTaskFinishedListener {
        void onTaskFinished();

    }
    public LoadingTask(ProgressBar progressBar, Context context, LoadingTaskFinishedListener finishedListener) {
        this.progressBar = progressBar;
        this.finishedListener = finishedListener;
        this.context = context;
    }

    private boolean resourcesDontAlreadyExist() {
        SharedPreferences sp = context.getSharedPreferences("mPrefs",context.MODE_PRIVATE);
        boolean firstStart = sp.getBoolean("firstStart", true);
        if (helper.getSpeakersArray().isEmpty() || helper.getLecturesArray().isEmpty() || firstStart){
            SharedPreferences.Editor se = sp.edit();
            se.putBoolean("firstStart", false);
            se.apply();
            return true;
        } return false;
    }

    private void downloadResources() {
        sessionInfoService = new SessionInfoService();
        downloadSpeakers();
        downloadLectures();
        delay();
        helper.close();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate();
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        finishedListener.onTaskFinished();
    }

    private void downloadLectures() {
        try {
            sessionInfoService.getLectureList(new Callback() {
                @Override
                public void onResponse(Response response) {
                    helper.deleteLectures();
                    lectureList = (ArrayList<Lecture>) response.body();
                    for (Lecture l : lectureList) {
                        helper.addLecture(l);
                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context, "Update lectures failed", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e){
            Toast.makeText(context,"error",Toast.LENGTH_SHORT).show();
        }
    }

    private void downloadSpeakers() {
        try {
            sessionInfoService.getSpeakerList(new Callback() {
                @Override
                public void onResponse(Response response) {
                    helper.deleteSpeakers();
                    speakerList = (ArrayList<Speaker>) response.body();
                    for (Speaker s : speakerList) {
                        helper.addSpeaker(s);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context, "Update speakers failed.", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e){
            Toast.makeText(context,"Update failed",Toast.LENGTH_SHORT).show();
        }
    }
    public void delay() {
        for (int i = 0; i < 30; i++) {

            int progress = (int) ((i / (float) 29) * 100);
            publishProgress(progress);

            try { Thread.sleep(40); } catch (InterruptedException ignore) {}
    }
}}