package pl.wroclaw.asi.sesja_linuksowa;

import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.Model.Speaker;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by sp0rk on 27.03.16.
 */
public interface SessionInfoApi {
    @GET("/~wojciech_czerpak/13_sesja/lectures.json")
    Call<List<Lecture>> getLectureList();

    @GET("/~wojciech_czerpak/13_sesja/speakers.json")
    Call<List<Speaker>> getSpeakerList();
}
