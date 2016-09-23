package de.comdirect.collabothon2016.collabothon2016.fragment;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;

/**
 * Created by A3286390 on 22.09.2016.
 */
public class GroupDetailsFragment extends Fragment implements ActionBar.TabListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.pager)
    ViewPager pager;

    private GroupPagerAdapter pagerAdapter;

    public GroupDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_group_details, container, false);
        ButterKnife.bind(this, rootView);

        pagerAdapter = new GroupPagerAdapter(getActivity().getSupportFragmentManager());

        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab()
                    .setText(pagerAdapter.getPageTitle(i))
                    .setTabListener(this));
        }

        return rootView;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyGroupsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupDetailsFragment newInstance(String param1, String param2) {
        GroupDetailsFragment fragment = new GroupDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // do nothing
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // do nothing
    }


    public static class GroupPagerAdapter extends FragmentPagerAdapter {
        public GroupPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment frag = null;

            switch (position) {
                case 0: // Overview
                    frag = OverviewFragment.newInstance(null, null);
                    break;
                case 1: // Portfolio
                    frag = PortfolioFragment.newInstance(null, null);
                    break;
                case 2: // Voting
                    frag = VotingFragment.newInstance(null, null);
                    break;
                case 3: // Leaderboard
                    frag = LeaderboardFragment.newInstance(null, null);
                    break;
                default:
                    throw new IllegalStateException("Unknown page position.");
            }

            return frag;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position) {
                case 0:
                    title = "OVERVIEW";
                    break;
                case 1:
                    title = "PORTFOLIO";
                    break;
                case 2:
                    title = "VOTING";
                    break;
                case 3:
                    title = "LEADERBOARD";
                    break;
                default:
                    throw new IllegalStateException("Unknown page position.");
            }
            return title;
        }
    }

}
