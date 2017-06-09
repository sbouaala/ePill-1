package com.gl52.android.epill.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dc on 2017/5/16.
 */

public class Medicament {
    private Integer id;
    private String name;
    private String frequence;
    private String duration;


    private static int sId = 100;

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    private List<Alarm> alarms = new LinkedList<Alarm>();
    /**
     *
     * @param alarm
     * allows a new alarm sto be added to a preexisting alarm
     */
    public void addAlarm(Alarm alarm) {
        alarms.add(alarm);
        Collections.sort(alarms);
    }


}

