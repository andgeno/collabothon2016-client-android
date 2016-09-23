package de.comdirect.collabothon2016.collabothon2016.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import de.comdirect.collabothon2016.collabothon2016.R;

public class ImageUtils {

    public static Drawable getGroupAvatar(Context context, int groupId) {
        Drawable drawable = null;
        switch (groupId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.wind);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.chemistry);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.farming);
                break;
            default:
                throw new IllegalStateException("Unknown group ID");
        }
        return drawable;
    }

    public static Drawable getUserAvatar(Context context, int userId) {
        Drawable drawable = null;
        switch (userId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.spy_icon);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.superman_icon);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.doctor_icon);
                break;
            default:
                throw new IllegalStateException("Unknown user ID");
        }
        return drawable;
    }

    public static Drawable getUserAvatarWithCrest(Context context, int userId) {
        Drawable drawable = null;
        switch (userId) {
            case 1:
                drawable = context.getResources().getDrawable(R.drawable.heisenberg_gold);
                break;
            case 2:
                drawable = context.getResources().getDrawable(R.drawable.man_silver);
                break;
            case 3:
                drawable = context.getResources().getDrawable(R.drawable.woman_bronze);
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
