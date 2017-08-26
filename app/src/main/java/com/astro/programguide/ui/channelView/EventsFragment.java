package com.astro.programguide.ui.channelView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.astro.programguide.Application;
import com.astro.programguide.R;
import com.astro.programguide.adapter.EventAdapter;
import com.astro.programguide.data.Comparators.EventNameComparator;
import com.astro.programguide.data.Comparators.EventNumberComparator;
import com.astro.programguide.data.models.Channel;
import com.astro.programguide.data.models.Event;
import com.astro.programguide.data.models.OnLoadMoreListener;
import com.astro.programguide.manager.ChannelManager;
import com.astro.programguide.manager.EventManager;
import com.astro.programguide.ui.base.BaseFragment;
import com.astro.programguide.util.contracts.EventsContract;
import com.astro.programguide.util.presenter.EventsPresenter;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hitesh on 8/22/17.
 */

public class EventsFragment extends BaseFragment implements EventsContract.View, OnLoadMoreListener {

    @BindView(R.id.recycler_view_tv_guide)
    RecyclerView            recyclerViewTVGuide;

    @BindView(R.id.spinner)
    Spinner                 spinner;

    @BindView(R.id.progress_events)
    ProgressBar             progressBarEvents;
    @Inject
    EventManager            eventManager;
    @Inject
    ChannelManager          channelManager;

    @BindView(R.id.tv_error)
    TextView                textViewError;

    EventAdapter            tvGuideAdapter;
    ArrayList<Channel>      channels;
    ArrayList<Event>        events            = new ArrayList<>();
    EventsPresenter         eventsPresenter;
    int                     channelsLoaded    = 0;
    boolean                 isNameSorted;
    boolean                 isNumberSorted;

    public static final int CHANNEL_THRESHOLD = 15;

    public static EventsFragment newInstance(int sectionNumber) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application.getInstance().getApplicationComponent().inject(this);
        eventsPresenter = new EventsPresenter(this);
        //Sustain COnfiguration Changes
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_tv_guide, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (savedInstanceState == null)
            eventsPresenter.getTvChannels();
        initializeViews();

    }

    private void initializeViews() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTVGuide.setLayoutManager(mLayoutManager);
        recyclerViewTVGuide.setItemAnimator(new DefaultItemAnimator());

        tvGuideAdapter = new EventAdapter(recyclerViewTVGuide, getActivity(), events);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewTVGuide.getContext(), mLayoutManager.getOrientation());

        recyclerViewTVGuide.addItemDecoration(dividerItemDecoration);
        recyclerViewTVGuide.setAdapter(tvGuideAdapter);
        tvGuideAdapter.setOnLoadMoreListener(this);

        final ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_text, getResources().getStringArray(R.array.sort));
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sortAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        //No need to reset channels ,as all channels are loaded
                        if (channelsLoaded == channels.size()) {
                            eventManager.sortByName(events);
                            tvGuideAdapter.notifyDataSetChanged();
                        } else {
                            isNameSorted = true;
                            isNumberSorted = false;
                            channelsLoaded = 0;
                            channelManager.sortByName(channels);
                            events = new ArrayList<>();
                            tvGuideAdapter.setEvents(events);
                            onLoadMore();
                        }
                        break;
                    case 2:
                        if (channelsLoaded == channels.size()) {
                            eventManager.sortByNumber(events);
                            tvGuideAdapter.notifyDataSetChanged();
                        } else {
                            isNameSorted = false;
                            isNumberSorted = true;
                            channelsLoaded = 0;
                            channelManager.sortByNumber(channels);
                            events = new ArrayList<>();
                            tvGuideAdapter.setEvents(events);
                            onLoadMore();
                        }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showChannels(ArrayList<Channel> channelResponse) {
        channels = channelResponse;
        onLoadMore();
    }

    @Override
    public void showTVGuide(ArrayList<Event> guideResponse) {
        if (isNameSorted)
            Collections.sort(guideResponse, new EventNameComparator());
        else if (isNumberSorted)
            Collections.sort(guideResponse, new EventNumberComparator());
        events.addAll(guideResponse);
        tvGuideAdapter.notifyDataSetChanged();
        //Does not show channel who does not have currently showing show
        if (channels.size() - channelsLoaded < CHANNEL_THRESHOLD) {
            //Last set of channels loaded
            channelsLoaded = channels.size();
        } else {
            channelsLoaded = channelsLoaded + CHANNEL_THRESHOLD;
        }
        tvGuideAdapter.setLoaded();

    }

    @Override
    public void showProgress() {
        progressBarEvents.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarEvents.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        textViewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadMore() {
        if (channelsLoaded < channels.size()) {
            ArrayList<String> channelsId = new ArrayList<>();
            for (int i = channelsLoaded; i < channelsLoaded + CHANNEL_THRESHOLD && i < channels.size(); i++) {
                channelsId.add(String.valueOf(channels.get(i).getChannelId()));
            }
            eventsPresenter.getTVGuide(channelsId);
        }
    }
}
