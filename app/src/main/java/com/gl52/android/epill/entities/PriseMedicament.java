package com.gl52.android.epill.entities;

import java.util.Date;

/**
 * Created by dc on 2017/6/7.
 */

public class PriseMedicament {
    private String id;
    private String medicamentId;
    private String ordonnanceId;
    private int hour;
    private int minute;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(String medicamentId) {
        this.medicamentId = medicamentId;
    }

    public String getOrdonnanceId() {
        return ordonnanceId;
    }

    public void setOrdonnanceId(String ordonnanceId) {
        this.ordonnanceId = ordonnanceId;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
