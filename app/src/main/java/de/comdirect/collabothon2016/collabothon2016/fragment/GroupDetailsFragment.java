package de.comdirect.collabothon2016.collabothon2016.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.BuildConfig;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.activity.MainActivity;
import de.comdirect.collabothon2016.collabothon2016.event.GroupSelectedEvent;
import de.comdirect.collabothon2016.collabothon2016.service.GroupService;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

import static rx.schedulers.Schedulers.newThread;

/**
 * Created by A3286390 on 22.09.2016.
 */
public class GroupDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int groupId;

    private GroupService groupService;

    public GroupDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        groupService = GroupService.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


//        Call<Group> request = groupService.getGroup(1);
//        Response<Group> response = null;
//        try {
//            response = request.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if(response.isSuccessful()) {
//            Log.d(BuildConfig.LOG_TAG, "response SUCCESS: " + response.body());
//        } else {
//            Log.d(BuildConfig.LOG_TAG, "response FAILURE: " + response.errorBody());
//        }

        Log.d(BuildConfig.LOG_TAG, "####### REST");

        groupService.getService().getGroup(0)
                .subscribeOn(newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(BuildConfig.LOG_TAG, "error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ResponseBody> response) {
                        Log.d(BuildConfig.LOG_TAG, "response: " + response.body());
                    }
                });


        ((MainActivity) getActivity()).getSupportActionBar().setTitle("GRUPPENNAMMMMMEEE");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_group_overview, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
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
