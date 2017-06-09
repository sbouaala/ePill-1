package com.gl52.android.epill.entities;

import android.app.Activity;
import android.content.Context;
import com.gl52.android.epill.utils.dbconnection.DbHelper;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by dc on 2017/5/16.
 */

public class OrdonnanceLab {
    private static OrdonnanceLab sOrdonnanceLab;
    private ArrayList<Ordonnance> mOrdonnances;
    private Ordonnance mTemporaryOrdonnance;
    private static int id = 100;

    public Ordonnance getTemporaryOrdonnance() {
        return mTemporaryOrdonnance;
    }

    public void setTemporaryOrdonnance(Ordonnance temporaryOrdonnance) {
        mTemporaryOrdonnance = temporaryOrdonnance;
    }

    private Context mAppContext;

        private DbHelper db;
        private static List<Long> tempIds; // Ids of the alarms to be deleted or edited
        private static String tempName; // Ids of the alarms to be deleted or edited

        public List<Long> getTempIds() { return Collections.unmodifiableList(tempIds); }

        public void setTempIds(List<Long> tempIds) { this.tempIds = tempIds; }

        public String getTempName() { return tempName; }

        public void setTempName(String tempName) { this.tempName = tempName; }

        public List<Medicament> getPills(Context c) {
            db = new DbHelper(c);
            List<Medicament> allPills = db.getAllPills();
            db.close();
            return allPills;
        }

        public long addPill(Context c, Medicament pill) {
            db = new DbHelper(c);
            long pillId = db.createPill(pill);
            pill.setId((int) pillId);
            db.close();
            return pillId;
        }

        public Medicament getPillByName(Context c, String pillName){
            db = new DbHelper(c);
            Medicament wantedPill = db.getPillByName(pillName);
            db.close();
            return wantedPill;
        }

        public void addAlarm(Context c, Alarm alarm, Medicament pill){
            db = new DbHelper(c);
            db.createAlarm(alarm, pill.getId());
            db.close();
        }

        public List<Alarm> getAlarms(Context c, int dayOfWeek) throws URISyntaxException {
            db = new DbHelper(c);
            List<Alarm> daysAlarms= db.getAlarmsByDay(dayOfWeek);
            db.close();
            Collections.sort(daysAlarms);
            return daysAlarms;
        }

        public List<Alarm> getAlarmByPill (Context c, String pillName) throws URISyntaxException {
            db = new DbHelper(c);
            List<Alarm> pillsAlarms = db.getAllAlarmsByPill(pillName);
            db.close();
            return pillsAlarms;
        }

        public boolean pillExist(Context c, String pillName) {
            db = new DbHelper(c);
            for(Medicament pill: this.getPills(c)) {
                if(pill.getName().equals(pillName))
                    return true;
            }
            return false;
        }

        public void deletePill(Context c, String pillName) throws URISyntaxException {
            db = new DbHelper(c);
            db.deletePill(pillName);
            db.close();
        }

        public void deleteAlarm(Context c, long alarmId) {
            db = new DbHelper(c);
            db.deleteAlarm(alarmId);
            db.close();
        }





        public Alarm getAlarmById(Context c, long alarm_id) throws URISyntaxException {
            db = new DbHelper(c);
            Alarm alarm = db.getAlarmById(alarm_id);
            db.close();
            return alarm;
        }

        public int getDayOfWeek(Context c, long alarm_id) throws URISyntaxException {
            db = new DbHelper(c);
            int getDayOfWeek = db.getDayOfWeek(alarm_id);
            db.close();
            return getDayOfWeek;
        }


    public static OrdonnanceLab get(Activity activity) {
    }

    public Ordonnance getOrdonnance(String ordonnanceId) {
    }
}


