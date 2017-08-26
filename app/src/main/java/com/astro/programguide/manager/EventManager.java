package com.astro.programguide.manager;

import android.content.Context;

import com.astro.programguide.Application;
import com.astro.programguide.data.Comparators.EventNameComparator;
import com.astro.programguide.data.Comparators.EventNumberComparator;
import com.astro.programguide.data.models.Event;
import com.astro.programguide.util.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hitesh on 8/23/17.
 */

public class EventManager {

    Context context;

    public EventManager(Context context) {
        this.context = context;
        Application.getInstance().getApplicationComponent().inject(this);

    }

    public ArrayList<Event> getCurrentAiredShow(ArrayList<Event> eventArrayList) {
        ArrayList<Event> currentAiredShowList = new ArrayList<>();
        for (Event event : eventArrayList) {
            //Check for current time in time duration
            try {
                if (Utils.isEventInCurrentTime(event)) {
                    currentAiredShowList.add(event);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return currentAiredShowList;
    }

    public Map<String, Object> getCurrentLiveEventMap(ArrayList<String> channelIds) {
        Map<String, Object> data = new HashMap<>();
        //Assuming start event lies betwee
        data.put("periodEnd", Utils.getTimeStamp(false, 3600 * 1000));
        data.put("periodStart", Utils.getTimeStamp(true, 3600 * 1000));
        data.put("channelId", channelIds);
        return data;
    }

    public ArrayList<Event> sortByName(ArrayList<Event> events) {
        Collections.sort(events, new EventNameComparator());
        return events;
    }

    public ArrayList<Event> sortByNumber(ArrayList<Event> events) {
        Collections.sort(events, new EventNumberComparator());
        return events;
    }
}
