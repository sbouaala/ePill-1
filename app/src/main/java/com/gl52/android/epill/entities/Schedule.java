package com.gl52.android.epill.entities;

import android.content.Context;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dc on 2017/6/7.
 */

public class Schedule {
    private static Schedule sSchedule;
    private ArrayList<PriseMedicament> mPrise;

    private Context mAppContext;

    public ArrayList<PriseMedicament> getPrise() {
        return mPrise;
    }

    public void setPrise(ArrayList<PriseMedicament> prise) {
        mPrise = prise;
    }

    public ArrayList<PriseMedicament> getPrise(Calendar calendar) {
        ArrayList<PriseMedicament> prise = new ArrayList<PriseMedicament>();
        Calendar c = Calendar.getInstance();
        for(PriseMedicament p:mPrise){
            c.setTime(p.getDate());
            if(c.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)){
                prise.add(p);
            }
        }
        return prise;
    }
    //initiate the schedule
    private Schedule(Context appContext){
        mAppContext = appContext;
        mPrise = new ArrayList<PriseMedicament>();
        PriseMedicament p1 = new PriseMedicament();
        p1.setDate(Calendar.getInstance().getTime());
        p1.setHour(8);
        p1.setMinute(20);
        p1.setOrdonnanceId("001");
        p1.setMedicamentId("0021");
        mPrise.add(p1);
    }

    public static Schedule get(Context c){
        if(sSchedule == null){
            sSchedule = new Schedule(c.getApplicationContext());
        }
        return  sSchedule;
    }
}
