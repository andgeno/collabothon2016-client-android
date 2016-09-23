package de.comdirect.collabothon2016.collabothon2016.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import de.comdirect.collabothon2016.collabothon2016.R;

public class ImageUtils {

    public static Drawable getGroupAvatar(Context context, int groupId) {
        Drawable drawable = null;
        switch (groupId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.ic_account_box_black_24dp);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.ic_menu_camera);
                break;
            default:
                throw new IllegalStateException("Unknown group ID");
        }
        return drawable;
    }

    public static Drawable getUserAvatar(Context context, int groupId) {
        Drawable drawable = null;
        switch (groupId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            default:
                throw new IllegalStateException("Unknown user ID");
        }
        return drawable;
    }

    public static Drawable getUserAvatarWithCrest(Context context, int groupId) {
        Drawable drawable = null;
        switch (groupId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.ic_avatar_dummy);
                break;
            default:
                throw new IllegalStateException("Unknown user ID");
        }
        return drawable;
    }

    public static Drawable getCrestByRank(Context context, int rank) {
        Drawable drawable = null;
        switch (rank) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.crest_gold);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.crest_silver);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.crest_bronze);
                break;
            default:
                throw new IllegalStateException("Unknown rank");
        }
        return drawable;
    }

}
