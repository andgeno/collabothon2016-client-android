package de.comdirect.collabothon2016.collabothon2016.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import de.comdirect.collabothon2016.collabothon2016.activity.MainActivity;
import de.comdirect.collabothon2016.collabothon2016.event.GroupSelectedEvent;

/**
 * Created by A3286390 on 22.09.2016.
 */
public class GroupDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int groupId;



    public GroupDetailsFragment() {


        ((MainActivity)getActivity()).getSupportActionBar().setTitle("GRUPPENNAMMMMMEEE");
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GroupOverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GroupDetailsFragment newInstance(String param1, String param2) {
        groupId = EventBus.getDefault().getStickyEvent(GroupSelectedEvent.class).groupId;
        GroupDetailsFragment fragment = new GroupDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
}
