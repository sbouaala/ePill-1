package com.gl52.android.epill.entities;

import android.content.Context;
import com.gl52.android.epill.utils.dbconnection.DbHelper;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dc on 2017/5/16.
 */

public class Ordonnance {
    private String id;
    private String name;
    private String mailDoc;
    private String description;
    //To generate an id, to be removed after the insert of the DBConnection
    private static int sId = 100;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private ArrayList<Medicament> mMedicaments;

    //Initiacte an ordonnance
    public Ordonnance(){
        this.mMedicaments = new ArrayList<Medicament>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailDoc() {
        return mailDoc;
    }

    public void setMailDoc(String mailDoc) {
        this.mailDoc = mailDoc;
    }

    public ArrayList<Medicament> getMedicaments() {
        return mMedicaments;
    }

    public void setMedicaments(ArrayList<Medicament> medicaments) {
        mMedicaments = medicaments;
    }

    private DbHelper db;

    public Medicament getPillByName(Context c, String pillName){
        db = new DbHelper(c);
        Medicament  wantedPill = db.getPillByName(pillName);
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

    public List<Medicament> getPills(Context c) {
        db = new DbHelper(c);
        List<Medicament> allPills = db.getAllPills();
        db.close();
        return allPills;
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



    public Alarm getAlarmById(Context c, long alarm_id) throws URISyntaxException{
        db = new DbHelper(c);
        Alarm alarm = db.getAlarmById(alarm_id);
        db.close();
        return alarm;
    }

    public int getDayOfWeek(Context c, long alarm_id) throws URISyntaxException{
        db = new DbHelper(c);
        int getDayOfWeek = db.getDayOfWeek(alarm_id);
        db.close();
        return getDayOfWeek;
    }
}

