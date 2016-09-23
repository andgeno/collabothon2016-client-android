package de.comdirect.collabothon2016.collabothon2016.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.BuildConfig;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.event.GroupSelectedEvent;
import de.comdirect.collabothon2016.collabothon2016.model.Group;
import de.comdirect.collabothon2016.collabothon2016.model.Vote;
import de.comdirect.collabothon2016.collabothon2016.service.VotingService;
import de.comdirect.collabothon2016.collabothon2016.viewadapter.VoteItemAdapter;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

import static rx.schedulers.Schedulers.newThread;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VotingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VotingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VotingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.votesRecyclerView)
    RecyclerView votesRecyclerView;

    private VoteItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Vote> votes;

    public VotingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VotingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VotingFragment newInstance(String param1, String param2) {
        VotingFragment fragment = new VotingFragment();
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
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_voting, container, false);
        ButterKnife.bind(this, rootView);

//        // TODO request data from API
//        votes = new ArrayList<>();
//        Vote v;
//        v = new Vote();
//
//        v.title = "name 1";
//        v.wertpapier = "isin 1";
//        votes.add(v);
//
//        v.title = "name 2";
//        v.wertpapier = "isin 2";
//        votes.add(v);
//
//        v.title = "name 3";
//        v.wertpapier = "isin 3";
//        votes.add(v);
//
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        votesRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        votesRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        votes = new ArrayList<>();
        mAdapter = new VoteItemAdapter(votes, vote -> mListener.voteItemSelected(vote));
        votesRecyclerView.setAdapter(mAdapter);


        refreshList();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(getContext(), "Refreshing", Toast.LENGTH_SHORT).show();
                refreshList();
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
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
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

        void voteItemSelected(Vote vote);

    }


    private void refreshList() {
        swipeRefreshLayout.setRefreshing(true);
        Group group = EventBus.getDefault().getStickyEvent(GroupSelectedEvent.class).group;
        VotingService.getInstance().getService().getVotes(group.id)
                .subscribeOn(newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {
                        // do nothing
                        Log.e(BuildConfig.LOG_TAG, "VOTES completed");
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(BuildConfig.LOG_TAG, "VOTES error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> response) {
                        Log.e(BuildConfig.LOG_TAG, "VOTES response: " + response.code());

                        String json = null;
                        try {
                            json = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Type collectionType = new TypeToken<List<Vote>>() {
                        }.getType();
                        List<Vote> votes = new Gson().fromJson(json, collectionType);
                        Log.e(BuildConfig.LOG_TAG, "VOTES response: " + votes);

                        VotingFragment.this.votes = votes;

                        mAdapter = new VoteItemAdapter(votes, vote -> mListener.voteItemSelected(vote));
                        votesRecyclerView.swapAdapter(mAdapter, true);
                    }
                });
    }

}
