package de.comdirect.collabothon2016.collabothon2016.viewadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.model.Group;

public class GroupItemAdapter extends RecyclerView.Adapter<GroupItemAdapter.ViewHolder> {
    private List<Group> mGroups;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.group_name)
        public TextView groupName;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GroupItemAdapter(List<Group> groups) {
        mGroups = groups;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GroupItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_group_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mTextView.setText(mGroups[position]);

//        Log.e(BuildConfig.LOG_TAG, "binding item: " + position);
        Group group = mGroups.get(position);
        holder.groupName.setText(group.groupName);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mGroups.size();
    }
}
