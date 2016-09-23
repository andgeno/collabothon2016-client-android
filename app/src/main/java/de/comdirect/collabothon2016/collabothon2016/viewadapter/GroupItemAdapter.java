package de.comdirect.collabothon2016.collabothon2016.viewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.model.Group;
import de.comdirect.collabothon2016.collabothon2016.util.ImageUtils;

public class GroupItemAdapter extends RecyclerView.Adapter<GroupItemAdapter.ViewHolder> {

    public interface OnGroupItemSelected {
        void groupItemSelected(Group group);
    }

    private OnGroupItemSelected mListener;

    private List<Group> mGroups;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.group_avatar)
        public ImageView groupAvatar;
        @BindView(R.id.group_name)
        public TextView groupName;
        @BindView(R.id.member_count)
        public TextView memberCount;
        @BindView(R.id.member_first_rank)
        public ImageView memberFirstRank;
        @BindView(R.id.member_second_rank)
        public ImageView memberSecondRank;
        @BindView(R.id.member_third_rank)
        public ImageView memberThirdRank;
        @BindView(R.id.group_description)
        public TextView groupDescription;

        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            ButterKnife.bind(this, v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GroupItemAdapter(List<Group> groups, OnGroupItemSelected listener) {
        mGroups = groups;
        mListener = listener;
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

        Group group = mGroups.get(position);
        setViewByGroup(holder, group);
        holder.view.setOnClickListener(v -> {
            mListener.groupItemSelected(group);
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mGroups.size();
    }


    public static void setViewByGroup(ViewHolder holder, Group group) {
        Context ctx = holder.view.getContext();

        int memberCount = 0;
//        Log.e(BuildConfig.LOG_TAG, "binding item: " + position);
        holder.groupAvatar.setImageDrawable(ImageUtils.getGroupAvatar(ctx, group.id));
        holder.groupName.setText(group.groupName);
        if (group.user != null) {
            memberCount = group.user.size();
        }
        holder.memberCount.setText("" + memberCount);
        holder.memberFirstRank.setImageDrawable(ImageUtils.getUserAvatarWithCrest(ctx, 1));
        holder.memberSecondRank.setImageDrawable(ImageUtils.getUserAvatarWithCrest(ctx, 2));
        holder.memberThirdRank.setImageDrawable(ImageUtils.getUserAvatarWithCrest(ctx, 3));
        holder.groupDescription.setText(group.description);
    }
}
