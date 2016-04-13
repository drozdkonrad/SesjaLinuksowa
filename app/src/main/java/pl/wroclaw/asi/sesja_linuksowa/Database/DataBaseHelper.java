package pl.wroclaw.asi.sesja_linuksowa.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import pl.wroclaw.asi.sesja_linuksowa.Model.Lecture;
import pl.wroclaw.asi.sesja_linuksowa.Model.Speaker;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


import java.sql.SQLException;
import java.util.List;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "database1.db";
    private static final int DATABASE_VERSION = 1;
    Context context;
    private DataBaseHelper databaseHelper = null;

    private Dao<Lecture, String> lectureDao;
    private Dao<Speaker, Integer> speakerDao;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, pl.wroclaw.asi.sesja_linuksowa.R.raw.ormlite_config);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, Lecture.class);
            TableUtils.createTable(connectionSource, Speaker.class);

        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, Lecture.class, true);
            TableUtils.dropTable(connectionSource, Speaker.class, true);
            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    public Dao<Speaker, Integer> getSpeakerDao() throws SQLException {
        if (speakerDao == null) {
            speakerDao = getDao(Speaker.class);
        }
        return speakerDao;
    }

    public Dao<Lecture, String> getLectureDao() throws SQLException {
        if (lectureDao == null) {
            lectureDao = getDao(Lecture.class);
        }
        return lectureDao;
    }
    private DataBaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
        }
        return databaseHelper;
    }

    public void addSpeaker(Speaker speaker) {
        try {
            final Dao<Speaker, Integer> speakerDao = getHelper().getSpeakerDao();
            speakerDao.create(speaker);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLecture(Lecture lecture) {
        try {
            final Dao<Lecture, String> lectureDao = getHelper().getLectureDao();
            lectureDao.create(lecture);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLecture(Lecture lecture) {
        try {
            final Dao<Lecture, String> lectureDao = getHelper().getLectureDao();
            DeleteBuilder<Lecture,String> db = lectureDao.deleteBuilder();
            db.where().eq("lecture_name", lecture.getName());
            db.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Speaker> getSpeakersArray() {
        Dao<Speaker, Integer> speakerDao;
        List<Speaker> speakerList;

        try {
            speakerDao = getHelper().getSpeakerDao();
            speakerList = speakerDao.queryForAll();
        }catch(SQLException e){
            e.printStackTrace();
            speakerList = null;
        }
        return speakerList;
    }

    public Speaker getSpeaker(int id) {
        Dao<Speaker, Integer> speakerDao;
        Speaker speaker;
        try {
            speakerDao = getHelper().getSpeakerDao();
            speaker = speakerDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            speaker = null;
        }
        return speaker;
    }

    public List<Lecture> getLecturesArray() {
        Dao<Lecture, String> lectureDao;
        List<Lecture> lectureList;

        try {
            lectureDao = getHelper().getLectureDao();
            QueryBuilder qb = lectureDao.queryBuilder()
                    .orderBy("lecture_day", true)
                    .orderBy("lecture_starttime", true);
            lectureList = qb.query();
        }catch(SQLException e){
            e.printStackTrace();
            lectureList = null;
        }
        return lectureList;
    }

    public void deleteSpeakers() {
        try {
            TableUtils.clearTable(getConnectionSource(),Speaker.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteLectures() {
        try {
            TableUtils.clearTable(getConnectionSource(),Lecture.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
