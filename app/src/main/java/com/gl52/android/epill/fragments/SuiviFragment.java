package com.gl52.android.epill.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gl52.android.epill.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dc on 2017/5/31.
 */

public class SuiviFragment extends Fragment{
    private MaterialCalendarView mCalendar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Schedule");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.info_suivi,container,false);
        mCalendar = (MaterialCalendarView) v.findViewById(R.id.calendarView);
        mCalendar.state().edit().setMinimumDate(CalendarDay.from(2017, 5, 1))
                .setMaximumDate(CalendarDay.from(2017, 7, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mCalendar.setSelectedDate(Calendar.getInstance().getTime());
        mCalendar.setOnDateChangedListener(new OnDateSelectedListener(){
        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
            SuiviListFragment priseFragment = (SuiviListFragment) getChildFragmentManager().findFragmentById(R.id.list_suivi);
            priseFragment.setDate(date.getCalendar());
        }
        });
        SuiviListFragment priseFragment = (SuiviListFragment) getChildFragmentManager().findFragmentById(R.id.list_suivi);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.list_suivi, new SuiviListFragment()).commit();
        return v;
    }

    //When the fragment is resumed, the calendar will set the last selected date to be focused
    //Here should set the current date to be selected(Or set the SuiviListFragment to correspond the calendar's selected date)
    @Override
    public void onResume() {
        super.onResume();
        mCalendar = (MaterialCalendarView) getView().findViewById(R.id.calendarView);
        mCalendar.setSelectedDate(Calendar.getInstance().getTime());
    }

    //When this fragment is detached, reset the ChildFragmentManager(is  not done in the origin Android code)
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
