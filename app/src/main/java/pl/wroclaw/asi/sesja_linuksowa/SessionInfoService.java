package pl.wroclaw.asi.sesja_linuksowa;

import java.io.IOException;


import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sp0rk on 27.03.16.
 */
public class SessionInfoService {
    private SessionInfoApi sessionInfoApi;

    public SessionInfoService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://tramwaj.asi.pwr.wroc.pl")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sessionInfoApi = retrofit.create(SessionInfoApi.class);
    }

    public void getLectureList(Callback callback) throws IOException {
        sessionInfoApi.getLectureList().enqueue(callback);
    }

    public void getSpeakerList(Callback callback) throws IOException {
        sessionInfoApi.getSpeakerList().enqueue(callback);
    }
}
