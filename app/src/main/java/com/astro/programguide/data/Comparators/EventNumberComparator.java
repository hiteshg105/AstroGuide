package com.astro.programguide.data.Comparators;

import com.astro.programguide.data.models.Event;

import java.util.Comparator;

/**
 * Created by hitesh on 8/24/17.
 */

public class EventNumberComparator implements Comparator<Event> {
    @Override
    public int compare(Event event1, Event event2) {
        return event1.getChannelStbNumber().compareTo(event2.getChannelStbNumber());
    }
}
