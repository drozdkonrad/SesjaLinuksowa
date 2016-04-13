package pl.wroclaw.asi.sesja_linuksowa.Database;

import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.Model.Speaker;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DataBaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[] {Speaker.class, Lecture.class};

    public static void main(String[] args) throws IOException, SQLException {

        writeConfigFile(new File("ormlite_config.txt"), classes);
    }
}
