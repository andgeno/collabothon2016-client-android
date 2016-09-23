package de.comdirect.collabothon2016.collabothon2016.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.event.GroupSelectedEvent;
import de.comdirect.collabothon2016.collabothon2016.model.Group;
import de.comdirect.collabothon2016.collabothon2016.viewadapter.GroupItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OverviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Group group;

    @BindView(R.id.interval)
    public TextView interval;
    @BindView(R.id.interval_amount)
    public TextView intervalAmount;
    @BindView(R.id.next_period_in)
    public TextView nextPeriodIn;
    @BindView(R.id.investing_since)
    public TextView investingSince;
    @BindView(R.id.payment_status_member_count)
    public TextView paymentStatusMemberCount;
    @BindView(R.id.payment_status_member_paid)
    public TextView paymentStatusMemberPaid;
    @BindView(R.id.overall_investment)
    public TextView overallInvestment;

    public OverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OverviewFragment newInstance(String param1, String param2) {
        OverviewFragment fragment = new OverviewFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
        ButterKnife.bind(this, rootView);

        group = EventBus.getDefault().getStickyEvent(GroupSelectedEvent.class).group;
        GroupItemAdapter.ViewHolder viewHolder = new GroupItemAdapter.ViewHolder(rootView);
        GroupItemAdapter.setViewByGroup(viewHolder, group);

        int numMembersPaid = 3; // TODO - DepotEngine aufrufen fuer Anzahl Bezahler in Gruppe
        int numMembersInGroup = 5;
        double amountOverall = 300; //group.amountOverall;

        interval.setText(group.interval);
        intervalAmount.setText("$" + group.amount);
        nextPeriodIn.setText(group.nextPeriod);
        investingSince.setText(group.investingSince);
        paymentStatusMemberPaid.setText("" + numMembersPaid);
        if (group.user != null) {
            numMembersInGroup = group.user.size();
        }
        paymentStatusMemberCount.setText("" + numMembersInGroup);
        overallInvestment.setText("$" + amountOverall);
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
    }
}
