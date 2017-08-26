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
import com.astro.programguide.adapter.ChannelAdapter;
import com.astro.programguide.data.models.Channel;
import com.astro.programguide.data.models.ChannelResponse;
import com.astro.programguide.data.models.RecyclerViewClickListener;
import com.astro.programguide.database.dao.ChannelDAO;
import com.astro.programguide.manager.ChannelManager;
import com.astro.programguide.ui.base.BaseFragment;
import com.astro.programguide.util.contracts.ChannelContract;
import com.astro.programguide.util.presenter.ChannelsPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hitesh on 8/20/17.
 */

public class ChannelFragment extends BaseFragment implements ChannelContract.View {

    ChannelsPresenter         channelsPresenter;

    @BindView(R.id.recycler_channels)
    RecyclerView              recyclerView;

    @BindView(R.id.spinner)
    Spinner                   spinner;

    @BindView(R.id.progress_channels)
    ProgressBar               progressBar;

    @BindView(R.id.tv_error)
    TextView                  textViewError;
    ChannelAdapter            channelAdapter;
    ChannelResponse           channelResponse;
    @Inject
    ChannelManager            channelManager;

    ArrayList<Channel>        channels;
    @Inject
    ChannelDAO                channelDAO;

    RecyclerViewClickListener recyclerViewClickListener = new RecyclerViewClickListener() {
                                                            @Override
                                                            public void click(View view, int position) {
                                                                channels.get(position).setFavourite(!channels.get(position).isFavourite());
                                                                channelDAO.replaceFavouriteChannel(channels.get(position));
                                                                channelAdapter.notifyItemChanged(position);
                                                            }
                                                        };

    public static ChannelFragment newInstance(int sectionNumber) {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application.getInstance().getApplicationComponent().inject(this);
        channelsPresenter = new ChannelsPresenter(this);
        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_channels, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        channelAdapter = new ChannelAdapter(getActivity(), channels, recyclerViewClickListener);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(channelAdapter);
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
                        channelManager.sortByName(channels);
                        channelAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        channelManager.sortByNumber(channels);
                        channelAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (savedInstanceState == null)
            channelsPresenter.getChannels();
    }

    @Override
    public void showChannels(ArrayList<Channel> channelResponse) {
        channels = channelResponse;
        channelAdapter.setChannels(channels);
        channelAdapter.notifyDataSetChanged();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showErrorMessage() {
        textViewError.setVisibility(View.VISIBLE);
    }

}
