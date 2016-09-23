package de.comdirect.collabothon2016.collabothon2016.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.activity.MainActivity;
import de.comdirect.collabothon2016.collabothon2016.event.GroupsReceivedEvent;
import de.comdirect.collabothon2016.collabothon2016.model.Group;
import de.comdirect.collabothon2016.collabothon2016.viewadapter.GroupItemAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyGroupsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyGroupsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyGroupsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Boolean fabButtonActive = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // FAB Animationen
//    Animation show_fab_1 = AnimationUtils.loadAnimation(((MainActivity) getActivity()).getApplication(), R.anim.fab1_show);
  //  Animation hide_fab_1 = AnimationUtils.loadAnimation(((MainActivity) getActivity()).getApplication(), R.anim.fab1_hide);


    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.fab_1)
    FloatingActionButton miniFab1;

    @BindView(R.id.fab_2)
    FloatingActionButton miniFab2;

    @BindView(R.id.fab_3)
    FloatingActionButton miniFab3;


    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.groupsRecyclerView)
    RecyclerView groupsRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private OnFragmentInteractionListener mListener;

    private List<Group> groups;

    public MyGroupsFragment() {
        // Required empty public constructor
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
    public static MyGroupsFragment newInstance(String param1, String param2) {
        MyGroupsFragment fragment = new MyGroupsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_groups, container, false);
        ButterKnife.bind(this, rootView);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("My Groups"); // FIXME Hack

        groups = EventBus.getDefault().getStickyEvent(GroupsReceivedEvent.class).groups;

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        groupsRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        groupsRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new GroupItemAdapter(groups, group -> mListener.groupItemSelected(group));
        groupsRecyclerView.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context application = ((MainActivity) getActivity()).getApplicationContext();
                FrameLayout.LayoutParams layoutParamsFab1 = (FrameLayout.LayoutParams) miniFab1.getLayoutParams();
                FrameLayout.LayoutParams layoutParamsFab2 = (FrameLayout.LayoutParams) miniFab2.getLayoutParams();
                FrameLayout.LayoutParams layoutParamsFab3 = (FrameLayout.LayoutParams) miniFab3.getLayoutParams();
                if (fabButtonActive){
                    fabButtonActive = false;
                    // miniFab1
                    layoutParamsFab1.rightMargin -= (int) (miniFab1.getWidth() * 1.7);
                    layoutParamsFab1.bottomMargin -= (int) (miniFab1.getHeight() * 0.25);
                    miniFab1.setLayoutParams(layoutParamsFab1);
                    Animation hideFab1 = AnimationUtils.loadAnimation(application, R.anim.fab1_hide);
                    miniFab1.startAnimation(hideFab1);
                    miniFab1.setClickable(false);
                    // miniFab2
                    layoutParamsFab2.rightMargin -= (int) (miniFab2.getWidth() * 1.5);
                    layoutParamsFab2.bottomMargin -= (int) (miniFab2.getHeight() * 1.5);
                    miniFab2.setLayoutParams(layoutParamsFab2);
                    Animation hideFab2 = AnimationUtils.loadAnimation(application, R.anim.fab2_hide);
                    miniFab2.startAnimation(hideFab2);
                    miniFab2.setClickable(false);
                    // miniFab3
                    layoutParamsFab3.rightMargin -= (int) (miniFab3.getWidth() * 0.25);
                    layoutParamsFab3.bottomMargin -= (int) (miniFab3.getHeight() * 1.75);
                    miniFab3.setLayoutParams(layoutParamsFab3);
                    Animation hideFab3 = AnimationUtils.loadAnimation(application, R.anim.fab3_hide);
                    miniFab3.startAnimation(hideFab3);
                    miniFab3.setClickable(false);
                }else {
                    fabButtonActive = true;
                    // miniFab1
                    layoutParamsFab1.rightMargin += (int) (miniFab1.getWidth() * 1.7);
                    layoutParamsFab1.bottomMargin += (int) (miniFab1.getHeight() * 0.25);
                    miniFab1.setLayoutParams(layoutParamsFab1);
                    Animation showFab1 = AnimationUtils.loadAnimation(application, R.anim.fab1_show);
                    miniFab1.startAnimation(showFab1);
                    miniFab1.setClickable(true);
                    // miniFab2
                    layoutParamsFab2.rightMargin += (int) (miniFab2.getWidth() * 1.5);
                    layoutParamsFab2.bottomMargin += (int) (miniFab2.getHeight() * 1.5);
                    miniFab2.setLayoutParams(layoutParamsFab2);
                    Animation showFab2 = AnimationUtils.loadAnimation(application, R.anim.fab2_show);
                    miniFab2.startAnimation(showFab2);
                    miniFab2.setClickable(true);
                    // miniFab3
                    layoutParamsFab3.rightMargin += (int) (miniFab3.getWidth() * 0.25);
                    layoutParamsFab3.bottomMargin += (int) (miniFab3.getHeight() * 1.7);
                    miniFab3.setLayoutParams(layoutParamsFab3);
                    Animation showFab3 = AnimationUtils.loadAnimation(application, R.anim.fab3_show);
                    miniFab3.startAnimation(showFab3);
                    miniFab3.setClickable(true);
                }
// Animation hide_fab_1 = AnimationUtils.loadAnimation(((MainActivity) getActivity()).getApplication(), R.anim.fab1_hide);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(getContext(), "Refreshing", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1500);
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        void groupItemSelected(Group group);
    }
}
