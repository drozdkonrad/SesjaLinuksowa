package pl.wroclaw.asi.sesja_linuksowa.Model;

/**
 * Created by sp0rk on 28.03.16.
 */
public class Partner {
    private int imgRID;
    private String webUrl;

    public Partner(String webUrl,int imgRID) {
        this.imgRID = imgRID;
        this.webUrl = webUrl;
    }

    public int getimgRID() {
        return imgRID;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
